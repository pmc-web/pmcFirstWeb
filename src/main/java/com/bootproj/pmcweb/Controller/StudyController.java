package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/study")
@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @PostMapping
    public HashMap createStudy(@ModelAttribute Study study)throws Exception{
        System.out.println(study.toString());
        HashMap<String, Long> resultMap = new HashMap<>();
        resultMap.put("insertId", studyService.createStudy(study));

        return resultMap;
    }
}