package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping("/study")
@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    @GetMapping
    public HashMap getStudyList(@RequestParam(value = "date")String value){
        return null;
    }

    @PostMapping
    public HashMap createStudy(@ModelAttribute Study study)throws Exception{
        HashMap<String, Long> resultMap = new HashMap<>();
        resultMap.put("insertId", studyService.createStudy(study));
        return resultMap;
    }

    @GetMapping("/{studyId}")
    public Study getStudyDetail(@PathVariable(value = "studyId")Long studyId){
        return studyService.getStudyDetail(studyId);
    }

    // TODO : 스터디 상태 변경 -> 스터디 마감, 스터디 삭제
    @PutMapping("/{studyId}")
    public Study closeStudy(@PathVariable(value="studyId")Long studyId){
        return studyService.putStudyStatus(studyId);
    }

}