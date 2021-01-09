package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Common.Response.StudyApiResponse;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.AttachmentService;
import com.bootproj.pmcweb.Service.StudyMemberService;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("/study")
@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    private final StudyMemberService studyMemberService;

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
        return new ResponseEntity(Header.OK(getStudyApiResponse(list)),HttpStatus.OK);
    }

    // @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping
    public ResponseEntity<Header<Study>> createStudy(@RequestBody Study study){
        Study createdStudy = studyService.createStudy(study);
        return new ResponseEntity(Header.OK(createdStudy), HttpStatus.CREATED);
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

    private List<StudyApiResponse> getStudyApiResponse(List<Study> studies){
        List<StudyApiResponse> studyApiResponses = new ArrayList<>();
        for (Study study : studies) {
            StudyApiResponse studyApiResponse = StudyApiResponse.builder()
                    .id(study.getId())
                    .title(study.getTitle())
                    .instTime(study.getInstTime())
                    .updtTime(study.getUpdtTime())
                    .status(study.getStatus())
                    .description(study.getDescription())
                    .startDate(study.getStartDate())
                    .endDate(study.getEndDate())
                    .evaluation(study.getEvaluation())
                    .type(study.getType())
                    .subjectId(study.getSubjectId())
                    .regionId(study.getRegionId())
                    .build();
            attachmentService.getStudyMainImage(study.getId()).ifPresent(attachment -> {
                studyApiResponse.setAttachmentPath(attachment.getName());
            });
            studyApiResponses.add(studyApiResponse);
        }
        return studyApiResponses;
    }
}