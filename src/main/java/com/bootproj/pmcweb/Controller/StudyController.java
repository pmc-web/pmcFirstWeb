package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Common.Request.StudyCreateRequest;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.AttachmentService;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequestMapping("/study")
@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    private final AttachmentService attachmentService;

    /**
     * Study REST API
     * by songi
     */

    @GetMapping
    public ResponseEntity<Header> getStudyList
    (@RequestParam(required = false, value = "page")Integer page,
     @RequestParam(required = false, value = "type")String type,
     @RequestParam(required = false, value = "date")String date,
     @RequestParam(required = false, value = "title")String title,
     @RequestParam(required = false, value = "location")String location,
     @RequestParam(required = false, value = "author")String author){
        if(page == null) page = 1;
        List<Study> list = studyService.getStudyList(page);//TODO: 수정
        return new ResponseEntity(Header.OK(list),HttpStatus.OK);
    }

    // @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping
    public ResponseEntity<Header> createStudy(@RequestBody Study study){
        log.info(study.toString());
        HashMap<String, Long> resultMap = new HashMap<>();
        resultMap.put("insertId", studyService.createStudy(study));
        return new ResponseEntity(Header.OK(resultMap), HttpStatus.CREATED);
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<Header> getStudyDetail(@PathVariable(value = "studyId")Long studyId){
        Study result = studyService.getStudyDetail(studyId);
        return new ResponseEntity(Header.OK(result), HttpStatus.OK);
    }

    // 스터디 상태 변경 -> 스터디 마감, 스터디 삭제
    @PutMapping("/{studyId}")
    public ResponseEntity<Header> changeStudyStatus(@PathVariable(value="studyId")Long studyId, @RequestParam(value = "status")String status){
        Study result = studyService.putStudyStatus(studyId, status);
        return new ResponseEntity(Header.OK(result),HttpStatus.OK);
    }
}