package com.bootproj.pmcweb.Controller.web;

import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContentWebController {

//    private final StudyService studyService;

    @GetMapping("/content/kakaoMap")
    public String changeContentView(Model model, @AuthenticationPrincipal User user){
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "MM월 dd일", Locale.KOREA );
        Date currentTime = new Date ();
        String sysdate = mSimpleDateFormat.format ( currentTime );
//        List<String> studyAllList = studyService.getAllList();
//        JSONArray jsonArray = new JSONArray(studyAllList);

//        model.addAttribute("studyAllList", studyAllList);
        model.addAttribute("content", 1);
        model.addAttribute("getdate", sysdate);

        return "main";
    }

}
