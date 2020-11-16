package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.PmcwebApplication;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

@ExtendWith(SpringExtension.class) //Junit4의 Runwith과 같은 기능을 하는 Junit5 어노테이션
@SpringBootTest(classes = PmcwebApplication.class) // Junit5 기준 Application Context사용할 때 사용
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Order를 붙일 때 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 진짜 데이터베이스에 테스트
public class UserMapperTest {
    @Autowired
    private AccountMapper userMapper;
    private static final String testEmail = "test@naver.com";

    @Test
    @Order(1)
    void createUser() {
        Account testUser = new Account(testEmail, "password", "name");
        userMapper.createUser(testUser);
        Account createdUser = userMapper.getUserById(testUser.getId());
        assertThat(createdUser.getEmail().equals(testUser.getEmail()));
    }

    @Test
    @Order(2)
    void getUserById() {
        Account testUser = new Account(testEmail, "password", "name");
        Account getUser = userMapper.getUserByEmail(testUser.getEmail());
        assertThat(testUser.getName().equals(getUser.getName()));
    }

    @Test
    @Order(3)
    void getUserList() {
        List<Account> userList = userMapper.getUserList();
        assertThat(userList.size() > 0);
    }

    @Test
    @Order(4)
    void deleteUser() {
        Account getUser = userMapper.getUserByEmail(testEmail);
        assertThat(userMapper.getUserById(getUser.getId())!=null);
        userMapper.deleteUser(getUser.getId());
        assertThat(userMapper.getUserById(getUser.getId())==null);
    }

}
