package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;

import java.util.List;

public interface UserService{
    public User getUser(Long id);
    public User getUserByEmail(String email);
    public List<User> getUsers();
    public User createUser(User user);
    public void deleteUser(Long id);
}
