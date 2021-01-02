package com.bootproj.pmcweb.Controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ChattingWebController {
    @GetMapping("/chatting")
    public String chattingView(){ // TODO FIX
        return "study/chat";
    }
}
