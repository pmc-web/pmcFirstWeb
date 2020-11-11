package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Network.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Network.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Network.Exception.SendEmailException;
import com.bootproj.pmcweb.Network.Header;
import com.bootproj.pmcweb.Service.AccountSecurityService;
import com.bootproj.pmcweb.Service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountSecurityService accountSecurityService;

    /**
     * Security signup login logout
     * made by kym
     */

    // 회원가입
    @GetMapping("/user/signup")
    public String getSignup(){
        return "user/register";
    }

    // 로그인
    @GetMapping("/user/login")
    public String getLogin(){
        return "user/login";
    }

    // 로그인 성공
    @GetMapping("/user/loginSuccess")
    public ModelAndView getLoginSuccess(@AuthenticationPrincipal User user){
        Account account = accountService.getUserByEmail(user.getUsername());
        String username = account.getName();
        ModelAndView mv = new ModelAndView("/main");
        mv.addObject("loginUser", username);

        return mv;
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

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public Header deleteUser(@PathVariable Long id){
        accountService.deleteUser(id);
        return Header.OK();
    }

    @GetMapping("/user/{id}")
    public Header<Account> getUser(@PathVariable Long id) {
        Account account = accountService.getUser(id);
        return Header.OK(account);
    }

    @PostMapping("/user/sendSignUpEmail")
    @ResponseBody
    public ResponseEntity<Header<Account>> sendSignUpEmail(@ModelAttribute Account account) throws DuplicateEmailException, SendEmailException{
        Account savedUser = accountSecurityService.save(account);
        return new ResponseEntity(Header.OK(savedUser), HttpStatus.CREATED);
    }

    @GetMapping("/user/signUpConfirm")
//    @ResponseBody
    public String signUpConfirm(@RequestParam(value="email") String email, @RequestParam(value="authKey") String authKey) throws NoMatchingAcountException, NoSuchFieldException {
        Account changedUser = accountService.signUpConfirm(authKey, email);
        // TODO: 로그인 페이지로 이동시키기
//        return new ResponseEntity(Header.OK(changedUser), HttpStatus.OK);
        return "redirect:/user/login";
    }
}
