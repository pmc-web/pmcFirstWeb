package com.bootproj.pmcweb.Controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ContentWebController {

    @GetMapping("/content/kakaoMap")
    public String changeContentView(Model model){
        model.addAttribute("content", 1);

        return "main";
    }

}
