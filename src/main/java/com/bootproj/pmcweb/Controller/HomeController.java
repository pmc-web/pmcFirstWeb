package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private StudyService studyService;

    @GetMapping("/test")
    public String Home(Model model){
        List<Study> studyList = studyService.selectStudyList();
        model.addAttribute("studyList", studyList);

        return "test";
    }

    @GetMapping("/")
    public String home(){

        return "main";
    }
}
