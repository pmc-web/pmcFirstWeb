package com.bootproj.pmcweb.Controller.web;

import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Attachment;
import com.bootproj.pmcweb.Service.AccountService;
import com.bootproj.pmcweb.Service.AttachmentService;
import com.bootproj.pmcweb.Service.RegionService;
import com.bootproj.pmcweb.Service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Controller
public class AccountWebController {

    private final AccountService accountService;
    private final AttachmentService attachmentService;
    private final RegionService regionService;
    private final SubjectService subjectService;

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

    // 비밀번호 변경 화면
    @GetMapping("/user/changePassword")
    public String changePassword() {
        return "user/changePassword";
    }

    /**
     * Profile
     * made by jiae
     */

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


    // 프로필 등록 화면
    @GetMapping("/user/register/profile")
    public ModelAndView getRegisterProfile(@AuthenticationPrincipal User user) throws NoMatchingAcountException {
        Account account = accountService.getUserByEmail(user.getUsername());
        ModelAndView mv = new ModelAndView("/user/registerProfile");
        mv.addObject("loginUser", account.getName());
        List<String> regions = regionService.getRegionDepth1();
        List<String> subjects = subjectService.getSubjectDepth1();
        mv.addObject("regions", regions);
        mv.addObject("subjects", subjects);
        mv.addObject("loginUser", account.getName());
        return mv;
    }

}
