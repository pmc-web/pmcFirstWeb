package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;

import java.util.List;
import java.util.Map;

public interface UserService{
    public User getUser(Long id);
    public User getUserByEmail(String email);
    public List<User> getUsers();
    public User createUser(User user);
    public void deleteUser(Long id);
    public void updateUserAuthKey(Map<String, String> map);
}
