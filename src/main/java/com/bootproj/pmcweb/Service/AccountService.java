package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Network.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Network.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Network.Exception.SendEmailException;

import java.util.List;
import java.util.Map;

public interface AccountService {
    public Account getUser(Long id);
    public Account getUserByEmail(String email);
    public List<Account> getUsers();
    public Account createUser(Account account);
    public void deleteUser(Long id);
    public void updateUserAuthKey(Map<String, String> map);
    public void updateUserStatus(Map<String, String> map);
    public String sendSignUpEmail(Account account) throws SendEmailException, DuplicateEmailException;
    public Account signUpConfirm(String authKey, String email) throws NoMatchingAcountException, NoSuchFieldException;
    public void updateUserPassword(Map<String, String> map);
}
