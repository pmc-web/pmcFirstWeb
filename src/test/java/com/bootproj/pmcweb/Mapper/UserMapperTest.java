package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.PmcwebApplication;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

@ExtendWith(SpringExtension.class) //Junit4의 Runwith과 같은 기능을 하는 Junit5 어노테이션
@SpringBootTest(classes = PmcwebApplication.class) // Junit5 기준 Application Context사용할 때 사용
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Order를 붙일 때 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 진짜 데이터베이스에 테스트

public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    private long testId = 2001L;

    @Test
    @Order(1)
    void createUser() {
        User testUser = new User(testId, "test@naver.com", "1234", UserStatus.REGISTERED.getTitle(), "test", UserRole.NORMAL.getTitle());
        userMapper.createUser(testUser);
        User createdUser = userMapper.getUserById(testId);
        assertThat(createdUser.getEmail().equals(testUser.getEmail()));
    }

    @Test
    @Order(2)
    void getUserById() {
        User testUser = new User(testId, "test@naver.com", "1234", UserStatus.REGISTERED.getTitle(), "test", UserRole.NORMAL.getTitle());
        User getUser = userMapper.getUserById(testId);
        assertThat(testUser.getEmail().equals(getUser.getEmail()));
    }

    @Test
    @Order(3)
    void getUserList() {
        List<User> userList = userMapper.getUserList();
        assertThat(userList.size() > 0);
    }

    @Test
    @Order(4)
    void deleteUser() {
        userMapper.deleteUser(testId);
        assertThat(userMapper.getUserById(testId)==null);
    }
}
