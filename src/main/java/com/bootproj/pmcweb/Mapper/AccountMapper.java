package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Account;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountMapper {
    public List<Account> getUserList();

    public Account getUserById(Long id);

    public Account getUserByEmail(String email);

    public void createUser(Account user);

    public void deleteUser(Long id);

    public void updateUserAuthKey(Map<String, String> map);

    public void updateUserStatus(Map<String, String> map);
}
