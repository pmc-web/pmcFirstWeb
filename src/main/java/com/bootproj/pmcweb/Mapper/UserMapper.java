package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    public List<User> getUserList();

    public User getUserById(Long id);

    public User getUserByEmail(String email);

    public void createUser(User user);

    public void deleteUser(Long id);

    public void updateUserAuthKey(Map<String, String> map);
}
