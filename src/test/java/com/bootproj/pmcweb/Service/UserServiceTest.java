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
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
    private Long id = 1000L;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    @Order(1)
    void createUser() {
        User user = new User(id, "test@naver.com", "1234", UserStatus.REGISTERED.getTitle(), "test", UserRole.NORMAL.getTitle());
        userServiceImpl.createUser(user);
        User findUser = userServiceImpl.getUser(id);
        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

    @Test
    @Order(2)
    void getUser() {
        User user = new User(id, "test@naver.com", "1234", UserStatus.REGISTERED.getTitle(), "test", UserRole.NORMAL.getTitle());
        User getUser = userServiceImpl.getUser(id);
        assertThat(user.getEmail().equals(getUser.getEmail()));
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
        User getUser = userServiceImpl.getUser(id);
        assertThat(userServiceImpl.getUser(id)!=null);
        userServiceImpl.deleteUser(id);
        assertThat(userServiceImpl.getUser(id)==null);
    }

}
