package com.bootproj.pmcweb.Controller.web;

import com.bootproj.pmcweb.Common.Response.StudyApiResponse;
import com.bootproj.pmcweb.Domain.*;
import com.bootproj.pmcweb.Domain.enumclass.MemberRole;
import com.bootproj.pmcweb.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.html.Option;
import java.util.*;


@Slf4j
@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
@RequiredArgsConstructor
public class StudyWebController {

    private final RegionService regionService;
    private final SubjectService subjectService;
    private final AccountService accountService;
    private final StudyMemberService studyMemberService;
    private final StudyService studyService;
    private final DateService dateService;
//    @GetMapping("/study/register")
//    public String getStudyRegister(){
//        return "study/study_register";
//    }

    @GetMapping("/study/register")
    public ModelAndView getCategory(@AuthenticationPrincipal User user) {
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
    public ModelAndView getDetail(@AuthenticationPrincipal User user, @RequestParam(value = "id") Long id) throws Exception {
        ModelAndView view = new ModelAndView("study/study_detail");
        try {
            // TODO : review 이렇게 여러 서비스에서 불러도 될까
            StudyApiResponse studyDetail = studyService.getStudyInfo(id).orElseThrow(() -> new NoSuchElementException());

            Region region = regionService.getRegionById(studyDetail.getRegionId()).orElseThrow(() -> new NoSuchElementException());
            Subject subject = subjectService.getSubjectById(id).orElseThrow(() -> new NoSuchElementException());
            List<StudyMember> studyMembers = studyMemberService.getStudyMembers(studyDetail.getId());
            List<Dates> schedules = dateService.getRecentDates(studyDetail.getId(), 3); // 최근 3개 일정

            // 내 이름 들고 오기
            String userName = "";
            if(user != null ) {
                Account myAccount = accountService.getUserByEmail(user.getUsername());
                userName = myAccount.getName();
            }
            log.info("{} {}", studyMembers, schedules);
            view.addObject("study", studyDetail);
            view.addObject("region", region);
            view.addObject("subject", subject);
            view.addObject("studyMembers", studyMembers);
            view.addObject("schedules", schedules);
            view.addObject("userName", userName);

            String userRole = MemberRole.GUEST.getTitle();
            if (user != null) {
                Account account = accountService.getUserByEmail(user.getUsername());
                userRole = studyMemberService.getMemberRole(id, account.getId());
            }
            view.addObject("userRole", userRole);
            return view;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
