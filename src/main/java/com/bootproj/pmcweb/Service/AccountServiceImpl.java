package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.Mapper.AccountMapper;
import com.bootproj.pmcweb.Common.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Common.Exception.SendEmailException;
import com.bootproj.pmcweb.Common.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    private final  MailSendService mailSendService;

    public Account getUser(Long id){
        Account account = accountMapper.getUserById(id);
        return account;
    }

    @Override
    public Account getUserByEmail(String email) {
        Account account = accountMapper.getUserByEmail(email);
        return account;
    }

    @Override
    public List<Account> getUsers() {
        List<Account> users = accountMapper.getUserList();
        return users;
    }

    public Account createUser(Account account){
        account.setStatus(UserStatus.UNREGISTERED.getTitle());
        account.setRole(UserRole.NORMAL.getTitle());
        account.setInstTime(new Date(System.currentTimeMillis()));

        accountMapper.createUser(account);

        return account;
    }

    @Override
    public void deleteUser(Long id) {
        accountMapper.deleteUser(id);
        return;
    }

    @Override
    public void updateUserAuthKey(Map<String, String> map) {
        accountMapper.updateUserAuthKey(map);
        return;
    }

    @Override
    public void updateUserStatus(Map<String, String> map) {
        accountMapper.updateUserStatus(map);
        return;
    }

    @Override
    public String sendSignUpEmail(Account account) throws SendEmailException, DuplicateEmailException {
        String msg = ResultCode.OK.getMessage();
        try {
            // DB에 정보 insert
            createUser(account);
            try {
                // 임의의 authKey 생성 & 이메일 발송
                String authKey = mailSendService.sendAuthMail(account.getEmail());
                Map<String, String> map = new HashMap<String, String>();
                map.put("email", account.getEmail());
                map.put("authKey", authKey);
                updateUserAuthKey(map);
            } catch (Exception e){
                // 메일 전송 실패 시 데이터 롤백
                log.info(e.getMessage());
                deleteUser(account.getId());
                msg = ResultCode.ERROR_SEND_EMAIL.getMessage();
            }
        } catch (Exception e){
            log.info(e.getMessage());
            msg = ResultCode.ERROR_EMAIL_DUPLICATE.getMessage();
        }
        return msg;
    }

    @Override
    public Account signUpConfirm(String authKey, String email) throws NoMatchingAcountException, NoSuchFieldException {
        // Email 값으로 유저를 찾아서 시크릿키가 같은지 확인하기
        if (authKey==null || email == null) throw new NoSuchFieldException("authKey나 email값이 올바르지 않습니다.");
        Account user = getUserByEmail(email);
        if (user==null) throw new NoMatchingAcountException(email + "은 존재하지 않는 유저입니다.");

        // 시크릿키가 일치할 경우 유저의 status를 REGISTERED로 변경하기
        log.info("authKey: " + authKey);
        log.info("user.authKey: " + user.getAuthKey());
        log.info(user.toString());
        if (authKey.equals(user.getAuthKey())){
            Map<String, String> map = new HashMap<String, String>();
            map.put("email", user.getEmail());
            map.put("status", UserStatus.REGISTERED.getTitle());
            updateUserStatus(map);
        }
        Account changedUser = getUserByEmail(email);
        return changedUser;
    }

    @Override
    public void updateUserPassword(Map<String, String> map) {
        accountMapper.updateUserPassword(map);
        return;
    }
}
