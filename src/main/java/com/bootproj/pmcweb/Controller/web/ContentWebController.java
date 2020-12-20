package com.bootproj.pmcweb.Controller.web;

import com.bootproj.pmcweb.Service.RegionService;
import com.bootproj.pmcweb.Service.SubjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContentWebController {

    private final RegionService regionService;
    private final SubjectService subjectService;

    @GetMapping("/content/kakaoMap")
    public String changeContentView(Model model, @AuthenticationPrincipal User user){
        List<String> regions = regionService.getRegionDepth1();
        List<String> subjects = subjectService.getSubjectDepth1();

        model.addAttribute("content", 1);
        model.addAttribute("regions", regions);
        model.addAttribute("subjects", subjects);

        return "main";
    }

}
