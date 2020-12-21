package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.PmcwebApplication;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class) //Junit4의 Runwith과 같은 기능을 하는 Junit5 어노테이션
@SpringBootTest(classes = PmcwebApplication.class) // Junit5 기준 Application Context사용할 때 사용
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Order를 붙일 때 사용
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 진짜 데이터베이스에 테스트
public class AccountMapperTest {
    @Autowired
    private AccountMapper userMapper;
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

        userMapper.createUser(account);
        Account createdUser = userMapper.getUserByEmail(testEmail);
        assertThat(createdUser.getEmail().equals(account.getEmail()));
    }

    @Test
    @Order(2)
    void getUserByEmail() {
        Account getUser = userMapper.getUserByEmail(testEmail);
        assertThat(testName.equals(getUser.getName()));
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
        assertThat(getUser!=null);
        userMapper.deleteUser(getUser.getId());
        assertThat(userMapper.getUserById(getUser.getId())==null);
    }

    @Test
    void updateUserAttachment() {
        String testEmail = "hirlawldo@naver.com";
        String testAttachmentId = "1";
        Account getUser = userMapper.getUserByEmail(testEmail);
        Assert.assertNull(getUser.getAttachmentId());
        Map<String, String> map = new HashMap<>();
        map.put("email", testEmail);
        map.put("attachmentId", testAttachmentId);
        userMapper.updateUserAttachment(map);
    }

    @Test
    void updateUserSubjectId() {
        String testEmail = "hirlawldo@naver.com";
        Map<String, String> map = new HashMap<>();
        map.put("email", testEmail);
        map.put("subjectId", "2");
        userMapper.updateUserSubjectId(map);
    }

    @Test
    void updateUserRegionId() {
        String testEmail = "hirlawldo@naver.com";
        Map<String, String> map = new HashMap<>();
        map.put("email", testEmail);
        map.put("regionId", "11665");
        userMapper.updateUserRegionId(map);
    }
}
