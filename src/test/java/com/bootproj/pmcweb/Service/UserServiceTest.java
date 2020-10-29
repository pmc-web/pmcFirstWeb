package com.bootproj.pmcweb.Service;


import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.Mapper.UserMapper;
import com.bootproj.pmcweb.PmcwebApplication;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userServiceImpl;
    private static final String testEmail = "test@naver.com";

    @Test
    @Order(1)
    void createUser() {
        User user = new User(testEmail, "1234", UserStatus.REGISTERED.getTitle(), "test", UserRole.NORMAL.getTitle());
        userServiceImpl.createUser(user);
        User findUser = userServiceImpl.getUserByEmail(user.getEmail());
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    @Order(2)
    void getUser() {
        User user = new User(testEmail, "1234", UserStatus.REGISTERED.getTitle(), "test", UserRole.NORMAL.getTitle());
        User getUser = userServiceImpl.getUserByEmail(testEmail);
        assertThat(testEmail.equals(getUser.getEmail()));
        assertThat(user.getName().equals(getUser.getName()));
    }

    @Test
    @Order(3)
    void getUsers() {
        List<User> userList = userServiceImpl.getUsers();
        assertThat(userList.size() > 0);
    }

    @Test
    @Order(4)
    void deleteUser() {
        User getUser = userServiceImpl.getUserByEmail(testEmail);
        assertThat(userServiceImpl.getUserByEmail(testEmail)!=null);
        userServiceImpl.deleteUser(getUser.getId());
        assertThat(userServiceImpl.getUserByEmail(testEmail)==null);
    }

}
