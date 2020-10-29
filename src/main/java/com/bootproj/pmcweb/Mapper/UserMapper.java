package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.User;

import java.util.List;

public interface UserMapper {
    public List<User> getUserList();

    public User getUserById(Long id);

    public User getUserByEmail(String email);

    public void createUser(User user);

    public void deleteUser(Long id);

}
