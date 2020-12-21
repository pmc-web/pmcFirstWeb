package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Common.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Common.Exception.SendEmailException;

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
    public void updateUserRegion(String email, Long regionId);
    public void updateUserSubject(String email, Long subjectId);
    public String sendSignUpEmail(Account account) throws SendEmailException, DuplicateEmailException;
    public Account signUpConfirm(String authKey, String email) throws NoMatchingAcountException, NoSuchFieldException;
    public void updateUserPassword(Map<String, String> map);
}
