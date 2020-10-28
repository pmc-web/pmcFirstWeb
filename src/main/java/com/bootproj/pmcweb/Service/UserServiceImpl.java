package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    public User getUser(Long id){
        User user = userMapper.getUserById(id);
        return user;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userMapper.getUserList();
        return users;
    }

    public void createUser(User user){
        userMapper.createUser(user);
        return;
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
        return;
    }
}
