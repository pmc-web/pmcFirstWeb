package com.bootproj.pmcweb.Service;


import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.PmcwebApplication;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private AccountServiceImpl userServiceImpl;
    private static final String testEmail = "test@naver.com";

    @Test
    @Order(1)
    void createUser() {
        Account user = new Account(testEmail, "password", "name");
        userServiceImpl.createUser(user);
        Account findUser = userServiceImpl.getUserByEmail(user.getEmail());
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    @Order(2)
    void getUser() {
        Account user = new Account(testEmail, "password", "name");
        Account getUser = userServiceImpl.getUserByEmail(testEmail);
        assertThat(testEmail.equals(getUser.getEmail()));
        assertThat(user.getName().equals(getUser.getName()));
    }

    @Test
    @Order(3)
    void getUsers() {
        List<Account> userList = userServiceImpl.getUsers();
        assertThat(userList.size() > 0);
    }

    @Test
    @Order(4)
    void deleteUser() {
        Account getUser = userServiceImpl.getUserByEmail(testEmail);
        assertThat(userServiceImpl.getUserByEmail(testEmail)!=null);
        userServiceImpl.deleteUser(getUser.getId());
        assertThat(userServiceImpl.getUserByEmail(testEmail)==null);
    }

}
