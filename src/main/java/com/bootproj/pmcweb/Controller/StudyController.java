package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/study")
@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @PostMapping
    public Study createStudy(@ModelAttribute Study study)throws Exception{
//        return studyService.createStudy(study);
        String title = study.getTitle();

        System.out.println(study.toString());

        return study;
    }
}