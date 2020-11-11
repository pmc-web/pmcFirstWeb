package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Network.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Network.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Network.Exception.SendEmailException;
import com.bootproj.pmcweb.Network.ResultCode;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountSecurityService implements UserDetailsService {

    @Autowired
    private AccountServiceImpl accountServiceimpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Account account = accountServiceimpl.getUserByEmail(username);
            if (!account.getName().equals(username)) {
                throw new UsernameNotFoundException(username + "이 등록되지 않았습니다.");
            }
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(account.getRole()));

            return new User(account.getEmail(), account.getPassword(), authorities);
    }

    public Account save(Account account) throws SendEmailException, DuplicateEmailException {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountServiceimpl.sendSignUpEmail(account);
    }

}
