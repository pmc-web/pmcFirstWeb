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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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

    // 프로필 화면
    @GetMapping("/user/profile")
    public String getProfile() {
        return "user/profile";
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
//    @ResponseBody
    public String sendSignUpEmail(@ModelAttribute @Valid Account account, BindingResult errors, Model model) throws DuplicateEmailException, SendEmailException{
        if (errors.hasErrors()) {
            // 유효성 통과 못한 필드와 메시지를 핸들링
            Map<String, String> validatorResult = accountSecurityService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "/user/register";
        }
        String result = accountSecurityService.save(account);
        model.addAttribute("msg", result);

        return "/user/registerConfirm";
    }

    @GetMapping("/user/signUpConfirm")
//    @ResponseBody
    public String signUpConfirm(@RequestParam(value="email") String email, @RequestParam(value="authKey") String authKey) throws NoMatchingAcountException, NoSuchFieldException {
        Account changedUser = accountService.signUpConfirm(authKey, email);
        accountService.signUpConfirm(authKey, email);
        // TODO: 로그인 페이지로 이동시키기
//        return new ResponseEntity(Header.OK(changedUser), HttpStatus.OK);
        return "redirect:/user/login";
    }
}
