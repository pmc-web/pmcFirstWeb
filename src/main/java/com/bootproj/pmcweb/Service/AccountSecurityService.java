package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Network.Exception.*;
import com.bootproj.pmcweb.Network.Request.LoginFailHandler;
import com.bootproj.pmcweb.Network.ResultCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@Slf4j
@Service
public class AccountSecurityService implements UserDetailsService {

    @Autowired
    private AccountServiceImpl accountServiceimpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Account account = accountServiceimpl.getUserByEmail(username);
        if(account == null){
            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");
        }
        if(account.getStatus().equals("UNREGISTERED")){
            throw new InternalAuthenticationServiceException("이메일이 인증되지 않았습니다.");
        }else{
            authorities.add(new SimpleGrantedAuthority(account.getRole()));
        }
        return new User(account.getEmail(), account.getPassword(), authorities);
    }

    public String save(Account account) throws SendEmailException, DuplicateEmailException {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountServiceimpl.sendSignUpEmail(account);
    }

    public void changePassword (String email, String oldPassword, String newPassword) throws PasswordNotMatchException {
        Account account = accountServiceimpl.getUserByEmail(email);
        if (passwordEncoder.matches(oldPassword, account.getPassword())){
            // 비밀번호 변경
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("email", email);
            map.put("password", passwordEncoder.encode(newPassword));
            accountServiceimpl.updateUserPassword(map);
        } else {
            throw new PasswordNotMatchException("패스워드를 잘못 입력하셨습니다.");
        }

        return;
    }

    // 회원가입 시, 유효성 체크
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

}
