package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.Common.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Common.Exception.PasswordNotMatchException;
import com.bootproj.pmcweb.Common.Exception.SendEmailException;
import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Service.AccountSecurityService;
import com.bootproj.pmcweb.Service.AccountService;
import com.bootproj.pmcweb.Service.AttachmentService;
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
import java.util.Optional;

@Slf4j
@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountSecurityService accountSecurityService;

    @Autowired
    private AttachmentService attachmentService;

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

    // 로그인 실패
    @PostMapping("/user/loginFail")
    public String getLoginFail(){
        return "user/loginFail";
    }

    // 로그아웃
    @GetMapping("/user/logout")
    public String getLogout(){
        return "user/login";
    }

    // 프로필 화면
    @GetMapping("/user/profile")
    public ModelAndView getProfile(@AuthenticationPrincipal User user) throws NoMatchingAcountException {
        Account account = accountService.getUserByEmail(user.getUsername());
        ModelAndView mv = new ModelAndView("/user/profile");
        mv.addObject("loginUser", account.getName());
        mv.addObject("loginUserEmail", account.getEmail());
        mv.addObject("loginUserId", account.getId());
        Optional<Attachment> attachment = attachmentService.getProfile(user.getUsername());
        attachment.ifPresentOrElse(
                (att) -> {
                    mv.addObject("profileImagePath", "/img/profile/" + account.getId() + "/" + att.getName());
                    log.info("/img/profile/" + account.getId() + "/" + att.getName());
                },
                () -> {
                    mv.addObject("profileImagePath", "/img/moim.jpg");
                    log.info("/img/moim.jpg");
                }
        );

        return mv;
    }

    // 비밀번호 변경 화면
    @GetMapping("/user/changePassword")
    public String changePassword() {
        return "user/changePassword";
    }

    /**
     * REST API
     * made by jiae
     */

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id){
        accountService.deleteUser(id);
        return "user/login";
    }

    @GetMapping("/user/{id}")
    public Header<Account> getUser(@PathVariable Long id) {
        Account account = accountService.getUser(id);
        return Header.OK(account);
    }

    @PostMapping("/user/changePassword")
    public String changePassword(@AuthenticationPrincipal User user, @RequestParam(value="oldPassword") String oldPassword, @RequestParam(value="newPassword") String newPassword) {
        try {
            accountSecurityService.changePassword(user.getUsername(), oldPassword, newPassword);
        } catch (PasswordNotMatchException e) {
            log.info(e.getMessage());
            return "redirect:/user/changePassword";
        }

        return "redirect:/user/profile";
    }

    @PostMapping("/user/sendSignUpEmail")
    @LogExecutionTime
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
    @LogExecutionTime
    public String signUpConfirm(@RequestParam(value="email") String email, @RequestParam(value="authKey") String authKey) throws NoMatchingAcountException, NoSuchFieldException {
        log.info("이메일 인증하는 부분");
        Account changedUser = accountService.signUpConfirm(authKey, email);
        accountService.signUpConfirm(authKey, email);
        return "/user/login";
    }
}
