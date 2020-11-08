package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Network.Header;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RequestMapping("/study")
@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    /**
     * Study REST API
     * by songi
     */

    @GetMapping
    public String getStudyList(){
        return "getStudyList";
    }

    @PostMapping
    public ResponseEntity<Header> createStudy(@ModelAttribute Study study){
        HashMap<String, Long> resultMap = new HashMap<>();
        log.info("result > ", resultMap);
        resultMap.put("insertId", studyService.createStudy(study));
        return new ResponseEntity(Header.OK(resultMap), HttpStatus.CREATED); // TODO : response 확인
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<Header> getStudyDetail(@PathVariable(value = "studyId")Long studyId){
        Study result = studyService.getStudyDetail(studyId);
        return new ResponseEntity(Header.OK(result), HttpStatus.OK); // TODO : response 확인
    }

    // 스터디 상태 변경 -> 스터디 마감, 스터디 삭제
    @PutMapping("/{studyId}")
    public ResponseEntity<Header> changeStudyStatus(@PathVariable(value="studyId")Long studyId, @RequestParam(value = "status")String status){
        Study result = studyService.putStudyStatus(studyId, status);
        return new ResponseEntity(Header.OK(result),HttpStatus.OK);
    }

}