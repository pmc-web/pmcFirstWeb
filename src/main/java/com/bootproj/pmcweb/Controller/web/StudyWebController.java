package com.bootproj.pmcweb.Controller.web;

import com.bootproj.pmcweb.Common.Exception.ForbiddenException;
import com.bootproj.pmcweb.Common.Response.StudyApiResponse;
import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Region;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.Subject;
import com.bootproj.pmcweb.Domain.enumclass.MemberRole;
import com.bootproj.pmcweb.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
@RequiredArgsConstructor
public class StudyWebController {

    private final RegionService regionService;
    private final SubjectService subjectService;
    private final StudyMemberService studyMemberService;
    private final StudyService studyService;

    @Autowired
    private AccountService accountService;
//    @GetMapping("/study/register")
//    public String getStudyRegister(){
//        return "study/study_register";
//    }

    @GetMapping("/study/register")
    public ModelAndView getCategory(@AuthenticationPrincipal User user) {
        if (user==null) throw new ForbiddenException();
        Account account = accountService.getUserByEmail(user.getUsername());
        List<String> regions = regionService.getRegionDepth1();
        List<String> subjects = subjectService.getSubjectDepth1();
        ModelAndView mv = new ModelAndView("study/study_register");
        mv.addObject("regions", regions);
        mv.addObject("subjects", subjects);
        mv.addObject("loginUser", account.getName());
        return mv;
    }

    @GetMapping("/study/detail")
    public ModelAndView getDetail(@AuthenticationPrincipal User user, @RequestParam(value = "id")Long id)throws Exception{
            ModelAndView adminView = new ModelAndView("study/study_detail");
            ModelAndView normalView = new ModelAndView("study/study_detail");
        try {
            StudyApiResponse studyDetail = studyService.getStudyInfo(id).orElseThrow(() -> new NoSuchElementException());

            adminView.addObject("study", studyDetail);
            normalView.addObject("study", studyDetail);

            if (user == null) return normalView;
            Account account = accountService.getUserByEmail(user.getUsername());
            String role = studyMemberService.getMemberRole(id, account.getId());

            if (MemberRole.ADMIN.getTitle().equals(role)) return adminView;
            return normalView;
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
