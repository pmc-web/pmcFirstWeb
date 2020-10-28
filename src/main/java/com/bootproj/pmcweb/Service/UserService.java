package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;

import java.util.List;

public interface UserService{
    public User getUser(Long id);
    public List<User> getUsers();
    public User addUser(User user);
}
