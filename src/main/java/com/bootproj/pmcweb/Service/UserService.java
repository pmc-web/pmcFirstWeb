package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserService {
    public User getUser(Long id);
    public User getUserByEmail(String email);
    public List<User> getUsers();
    public void createUser(User user);
    public void deleteUser(Long id);

}
