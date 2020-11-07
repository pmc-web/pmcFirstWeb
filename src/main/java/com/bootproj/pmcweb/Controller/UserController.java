package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.Network.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Network.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Network.Exception.SendEmailException;
import com.bootproj.pmcweb.Network.Header;
import com.bootproj.pmcweb.Network.ResultCode;
import com.bootproj.pmcweb.Service.MailSendService;
import com.bootproj.pmcweb.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/page/{name}")
    public String getPage(@PathVariable String name){

        return "user/" + name;
    }

    /**
     * Go to Page include Security
     * made by kym
     */

    // 회원가입
//    @PostMapping("/user/signup")
    @GetMapping("/user/signup")
    public String getSignUp(){
//        return "redirect:/user/login";
        return "user/register";
    }

    // 로그인
    @GetMapping("/user/login")
    public String getLogin(){
        return "user/login";
    }

    // 로그아웃
    @GetMapping("/user/logout")
    public String getLogout(){
        return "user/login";
    }

    /**
     * REST API
     * made by jiae
     */

    @GetMapping("/users")
    @ResponseBody
    public Header<List<User>> list(){
        List<User> users = userService.getUsers();
        return Header.OK(users);
    }


    @DeleteMapping("/user/{id}")
    @ResponseBody
    public Header deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return Header.OK();
    }

    @GetMapping("/user/{id}")
    public Header<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return Header.OK(user);
    }

    @PostMapping("/user/sendSignUpEmail")
    @ResponseBody
    public ResponseEntity<Header<User>> sendSignUpEmail(@ModelAttribute User user) throws DuplicateEmailException, SendEmailException{
        User savedUser = userService.sendSignUpEmail(user);
        return new ResponseEntity(Header.OK(savedUser), HttpStatus.CREATED);
    }

    @GetMapping("/user/signUpConfirm")
    @ResponseBody
    public ResponseEntity<Header> signUpConfirm(@RequestParam(value="email") String email, @RequestParam(value="authKey") String authKey) throws NoMatchingAcountException, NoSuchFieldException {
        User changedUser = userService.signUpConfirm(authKey, email);
        // TODO: 로그인 페이지로 이동시키기
        return new ResponseEntity(Header.OK(changedUser), HttpStatus.OK);
    }
}
