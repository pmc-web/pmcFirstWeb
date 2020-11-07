package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    public User createUser(User user){
        userMapper.createUser(user);
        return user;
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
}
