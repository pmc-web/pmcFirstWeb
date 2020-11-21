package com.bootproj.pmcweb.Controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
public class StudyWebController {
    // 회원가입
    @GetMapping("/study/register")
    public String getStudyRegister(){
        return "study/study_register";
    }
}
