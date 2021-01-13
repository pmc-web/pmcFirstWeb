package com.bootproj.pmcweb.Controller.web;

import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChattingWebController {

    final private StudyService studyService;

    @GetMapping("/chatting")
    public String chattingView( Model model, @AuthenticationPrincipal User user) throws Exception{
        try {
//            Study study = studyService.getStudyDetail(studyId).orElseThrow(()-> new NoMatchingAcountException("해당하는 스터디가 없습니다"));
//            model.addAttribute("study", study);
            return "study/chat";
        }catch (Exception e){
            log.warn(e.getMessage());
            return "/"; // TODO : code review 확인
        }
    }
}
