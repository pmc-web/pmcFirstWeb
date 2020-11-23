package com.bootproj.pmcweb.Controller.web;

import com.bootproj.pmcweb.Domain.Region;
import com.bootproj.pmcweb.Domain.Subject;
import com.bootproj.pmcweb.Service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Slf4j
@Controller // Rest Controller는 response 바디를 가지고, Controller는 가지지 않음.
public class StudyWebController {

    @Autowired
    private CategoryService categoryService;

//    @GetMapping("/study/register")
//    public String getStudyRegister(){
//        return "study/study_register";
//    }

    @GetMapping("/study/register")
    public ModelAndView getCategory(){
        List<Region> regions = categoryService.getAllRegions();
        List<Subject> subjects = categoryService.getAllSubjects();
        ModelAndView mv = new ModelAndView("study/study_register");
        mv.addObject("regions", regions);
        mv.addObject("subjects", subjects);
        return mv;
    }

}
