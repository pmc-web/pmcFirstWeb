package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Aspect.LogExecutionTime;
import com.bootproj.pmcweb.Common.Request.ProfileUpdateApiRequest;
import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.Common.Exception.DuplicateEmailException;
import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Common.Exception.PasswordNotMatchException;
import com.bootproj.pmcweb.Common.Exception.SendEmailException;
import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.AccountSecurityService;
import com.bootproj.pmcweb.Service.AccountService;
import com.bootproj.pmcweb.Service.AttachmentService;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final AccountSecurityService accountSecurityService;

    private final AttachmentService attachmentService;

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
        return "redirect:/user/login";
    }

    @PostMapping("/user/updateProfile")
    public ResponseEntity<Header> updateProfile(@RequestBody ProfileUpdateApiRequest request, @AuthenticationPrincipal User user){
        accountService.updateUserRegion(user.getUsername(), request.getRegionId());
        accountService.updateUserSubject(user.getUsername(), request.getSubjectId());
        Account account = accountService.getUserByEmail(user.getUsername());
        return new ResponseEntity(Header.OK(account), HttpStatus.OK);
    }


}
