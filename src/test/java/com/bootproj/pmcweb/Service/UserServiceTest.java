package com.bootproj.pmcweb.Service;


import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.Mapper.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @BeforeEach
    public void beforeEach(){
        userService = new UserServiceImpl();
//        userMapper = new UserMapper();
    }

    @AfterEach
    public void afterEach(){
    }

    @Test
    void createUser() {
        Long id = 1000L;
        User user = new User(id, "test@naver.com", "1234", UserStatus.REGISTERED.getTitle(), "test", UserRole.NORMAL.getTitle());
        userService.createUser(user);
        User findUser = userService.getUser(id);
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    void getUser() {
    }

    @Test
    void getUsers() {
    }



    @Test
    void deleteUser() {
    }
}
