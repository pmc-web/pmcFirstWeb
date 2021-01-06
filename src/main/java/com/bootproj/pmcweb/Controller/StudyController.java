package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Exception.FileSaveException;
import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
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
import java.util.Optional;

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
     @RequestParam(required = false, value = "author")String author) throws Exception{
        try {
            if(page == null) page = 1;
            List<Study> list = studyService.getStudyList(page); // TODO : ~
            return new ResponseEntity(Header.OK(list),HttpStatus.OK);
        }catch (Exception e){
            log.warn(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    // TODO : 익셉션 추가해야 하는 사항: 생성할 때 스터디장이 여러개의 진행중인 스터디를 지니고 있을 경우
    // TODO : file create하는건 study create하는거에서 분리해서 따로 호출하는 방향으로 진행
    // TODO study detail page는 create할 때 study member에 호스트인 애들을 같이 넣어줄 것
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Header> createStudy(@ModelAttribute StudyCreateRequest study){
        HashMap<String, Long> resultMap = new HashMap<>();
        try {
            resultMap.put("insertId", studyService.createStudy(study));
            if (study.getImage()!= null) attachmentService.uploadStudyMainImage(study.getImage(), study.getId());
        }catch (Exception e){
            throw new FileSaveException(e.getMessage());
        }
        return new ResponseEntity(Header.OK(resultMap), HttpStatus.CREATED);
    }

    @GetMapping("/{studyId}")
    public ResponseEntity<Header> getStudyDetail(@PathVariable(value = "studyId")Long studyId) throws NoMatchingAcountException {
        Optional<Study> study = studyService.getStudyDetail(studyId);
        study.orElseThrow(()-> new NoMatchingAcountException("해당하는 스터디가 존재하지 않습니다."));
        return new ResponseEntity(Header.OK(study), HttpStatus.OK);
    }

    // 스터디 상태 변경 -> 스터디 마감, 스터디 삭제
    @PutMapping("/{studyId}")
    public ResponseEntity<Header> changeStudyStatus(@PathVariable(value="studyId")Long studyId, @RequestParam(value = "status")String status) throws Exception{
        try{
            Optional<Study> study = studyService.putStudyStatus(studyId, status);
            return new ResponseEntity(Header.OK(study),HttpStatus.OK);
        }catch (Exception e){
            log.warn(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}