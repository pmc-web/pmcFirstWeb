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

    @Autowired
    private MailSendService mailSendService;

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
    public ResponseEntity<Header<User>> postUser(@ModelAttribute User user) throws DuplicateEmailException, SendEmailException{
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
    public ResponseEntity<Header> signUpConfirm(@RequestParam(value="email") String email, @RequestParam(value="authKey") String authKey) throws NoMatchingAcountException {
        // Email 값으로 유저를 찾아서 시크릿키가 같은지 확인하기
        if (authKey==null || email == null) return new ResponseEntity(Header.Error(ResultCode.REQUEST_ERROR_INVALID_INPUT_VALUE), HttpStatus.BAD_REQUEST);
        User user = userService.getUserByEmail(email);
        if (user==null) throw new NoMatchingAcountException(email + "은 존재하지 않는 유저입니다.");

        // 시크릿키가 일치할 경우 유저의 status를 REGISTERED로 변경하기
        log.info("authKey: " + authKey);
        log.info("user.authKey: " + user.getAuthKey());
        log.info(user.toString());
        if (authKey.equals(user.getAuthKey())){
            Map<String, String> map = new HashMap<String, String>();
            map.put("email", user.getEmail());
            map.put("status", UserStatus.REGISTERED.getTitle());
            userService.updateUserStatus(map);
        }
        User changedUser = userService.getUserByEmail(email);

        // TODO: 로그인 페이지로 이동시키기
        return new ResponseEntity(Header.OK(changedUser), HttpStatus.OK);
    }
}
