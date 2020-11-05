package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.User;
import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import com.bootproj.pmcweb.Network.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Network.Exception.GlobalExceptionHandler;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailSendService mailSendService;

    @GetMapping("/page/{name}")
    public String getPage(@PathVariable String name){

        return "user/" + name;
    }

    @GetMapping("/users")
    @ResponseBody
    public Header<List<User>> list(){
        List<User> users = userService.getUsers();
        return Header.OK(users);
    }

    @PostMapping("/user")
    @ResponseBody
//    public void postUser(@RequestBody User user){
    public Header<User> postUser(@ModelAttribute User user) throws DuplicateEmailException{
        User savedUser;
        try {
            User insertUser = new User(user.getEmail(), user.getPassword(), user.getName());
            savedUser = userService.createUser(insertUser);
            log.info("user value :"+savedUser);
        } catch (Exception e){
            throw new DuplicateEmailException(e.getMessage());
        }

        return Header.OK(savedUser);
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

    @PostMapping("/user/signUp")
    @ResponseBody
    public ResponseEntity<Header<User>> signUp(@ModelAttribute User user) throws DuplicateEmailException, SendEmailException{
        User savedUser;
        try {
            // DB에 정보 insert
            User insertUser = new User(user.getEmail(), user.getPassword(), user.getName());
            savedUser = userService.createUser(insertUser);
            log.info("user value :"+savedUser);
        } catch (Exception e){
            throw new DuplicateEmailException(e.getMessage());
        }

        try {
            // 임의의 authKey 생성 & 이메일 발송
            String authKey = mailSendService.sendAuthMail(user.getEmail());
            Map<String, String> map = new HashMap<String, String>();
            map.put("email", user.getEmail());
            map.put("authKey", authKey);
            userService.updateUserAuthKey(map);
        } catch (Exception e){
            // 메일 전송 실패 시 데이터 롤백
            userService.deleteUser(savedUser.getId());
            throw new SendEmailException(e.getMessage());
        }
        return new ResponseEntity(Header.OK(savedUser), HttpStatus.CREATED);
    }

    @GetMapping("/user/signUpConfirm")
    @ResponseBody
    public Header signUpConfirm(@RequestParam(value="email") String email, @RequestParam(value="authKey") String authKey){
        // TODO: Email 값으로 유저를 찾아서 시크릿키가 같은지 확인하기

        // TODO: 시크릿키가 일치할 경우 유저의 status를 REGISTERED로 변경하기

        // TODO: 로그인 페이지로 이동시키기
        return Header.OK();
    }
}
