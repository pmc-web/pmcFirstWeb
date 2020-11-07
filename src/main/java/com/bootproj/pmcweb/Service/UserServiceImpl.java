package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.Mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

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
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
        return;
    }
}
