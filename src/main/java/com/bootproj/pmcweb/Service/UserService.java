package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Network.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Network.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Network.Exception.SendEmailException;

import java.util.List;
import java.util.Map;

public interface UserService{
    public User getUser(Long id);
    public User getUserByEmail(String email);
    public List<User> getUsers();
    public void createUser(User user);
    public void deleteUser(Long id);
    public void updateUserAuthKey(Map<String, String> map);
    public void updateUserStatus(Map<String, String> map);
    public User sendSignUpEmail(User user) throws SendEmailException, DuplicateEmailException;
    public User signUpConfirm(String authKey, String email) throws NoMatchingAcountException, NoSuchFieldException;
}
