package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.CustomUserDetails;
import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.Mapper.UserMapper;
import com.bootproj.pmcweb.Network.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Network.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Network.Exception.SendEmailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private MailSendService mailSendService;

    public User getUser(Long id){
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userMapper.getUserByEmail(email);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userMapper.getUserList();
        return users;
    }

    public void createUser(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setStatus(UserStatus.REGISTERED.getTitle());
        user.setRole(UserRole.NORMAL.getTitle());

        userMapper.createUser(user);
        return;
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
        return;
    }

    @Override
    public void updateUserAuthKey(Map<String, String> map) {
        userMapper.updateUserAuthKey(map);
        return;
    }

    @Override
    public void updateUserStatus(Map<String, String> map) {
        userMapper.updateUserStatus(map);
        return;
    }

    @Override
    public User sendSignUpEmail(User user) throws SendEmailException, DuplicateEmailException {
        User insertUser = new User(user.getEmail(), user.getPassword(), user.getName());;

        try {
            // DB에 정보 insert
            createUser(insertUser);
        } catch (Exception e){
            throw new DuplicateEmailException(e.getMessage());
        }

        try {
            // 임의의 authKey 생성 & 이메일 발송
            String authKey = mailSendService.sendAuthMail(user.getEmail());
            Map<String, String> map = new HashMap<String, String>();
            map.put("email", user.getEmail());
            map.put("authKey", authKey);
            updateUserAuthKey(map);
        } catch (Exception e){
            // 메일 전송 실패 시 데이터 롤백
            deleteUser(insertUser.getId());
            throw new SendEmailException(e.getMessage());
        }
        return null;
    }

    @Override
    public User signUpConfirm(String authKey, String email) throws NoMatchingAcountException, NoSuchFieldException {
        // Email 값으로 유저를 찾아서 시크릿키가 같은지 확인하기
        if (authKey==null || email == null) throw new NoSuchFieldException("authKey나 email값이 올바르지 않습니다.");
        User user = getUserByEmail(email);
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
        User changedUser = getUserByEmail(email);
        return changedUser;
    }
}
