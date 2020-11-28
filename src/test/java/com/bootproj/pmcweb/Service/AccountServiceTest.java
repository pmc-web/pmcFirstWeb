package com.bootproj.pmcweb.Service;


import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.PmcwebApplication;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {

    @Autowired
    private AccountServiceImpl userServiceImpl;
    private static final String testEmail = "test@naver.com";
    private static final String testName = "test";

    @Test
    @Order(1)
    void createUser() {
        Account account = Account.builder()
                .name(testName)
                .email(testEmail)
                .password("1234")
                .status(UserStatus.REGISTERED.getTitle())
                .role(UserRole.NORMAL.getTitle())
                .instTime(new Date(System.currentTimeMillis()))
                .build();
        userServiceImpl.createUser(account);
        Account findUser = userServiceImpl.getUserByEmail(account.getEmail());
        assertThat(account.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    @Order(2)
    void getUser() {
        Account getUser = userServiceImpl.getUserByEmail(testEmail);
        assertThat(testEmail.equals(getUser.getEmail()));
        assertThat(testName.equals(getUser.getName()));
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
