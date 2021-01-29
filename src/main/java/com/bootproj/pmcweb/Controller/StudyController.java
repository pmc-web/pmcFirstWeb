package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Common.Exception.FileSaveException;
import com.bootproj.pmcweb.Common.Exception.NoMatchingAcountException;
import com.bootproj.pmcweb.Common.Header;
import com.bootproj.pmcweb.Common.Response.StudyApiResponse;
import com.bootproj.pmcweb.Domain.Account;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.StudyMember;
import com.bootproj.pmcweb.Service.AccountService;
import com.bootproj.pmcweb.Service.AttachmentService;
import com.bootproj.pmcweb.Service.StudyMemberService;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.tools.reflect.CannotCreateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@RequestMapping("/study")
@RestController
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    private final AttachmentService attachmentService;

    private final StudyMemberService studyMemberService;

    private final AccountService accountService;

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
     @RequestParam(required = false, value = "author")String author,
     @RequestParam(required = false, value = "inputData")String word) throws Exception{
        try {
            if(page == null) page = 1;
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("page", page);
            if(word != null && word != ""){
                paramMap.put("word", word);
            }
            //List<Study> list = studyService.getStudyList(page); // TODO : ~
            List<Study> list = studyService.getStudyList(paramMap);

            return new ResponseEntity(Header.OK(getStudyApiResponse(list)),HttpStatus.OK);
        }catch (Exception e){
            log.warn(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    // TODO : 익셉션 추가해야 하는 사항: 생성할 때 스터디장이 여러개의 진행중인 스터디를 지니고 있을 경우
    // TODO : file create하는건 study create하는거에서 분리해서 따로 호출하는 방향으로 진행
    // TODO study detail page는 create할 때 study member에 호스트인 애들을 같이 넣어줄 것
    @PostMapping
    public ResponseEntity<Header> createStudy(@RequestBody Study study, @AuthenticationPrincipal User user) throws CannotCreateException {
        Study createdStudy;
        try {
            createdStudy = studyService.createStudy(study);
            // 스터디 생성시 studyMember 테이블에 amdin 데이터 추가
            Account account = accountService.getUserByEmail(user.getUsername());
            StudyMember studyMember = StudyMember.builder().studyRole("ADMIN").studyId(createdStudy.getId()).userId(account.getId()).build();
            studyMemberService.joinStudy(studyMember);
        }catch (Exception e){
            throw new CannotCreateException(e.getMessage());
        }
        return new ResponseEntity(Header.OK(createdStudy), HttpStatus.CREATED);

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
        }catch (Exception e){ // TODO: exception 명시, roll back logic 구
            log.warn(e.getMessage());
            throw new Exception(e.getMessage());
        }
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
                studyApiResponse.setAttachmentPath(attachment.getPath());
            });
            studyApiResponses.add(studyApiResponse);
        }
        return studyApiResponses;
    }

    // 지도에서 데이터 보여주기 위한 스터디 조회
    @GetMapping("/mapData")
    public ResponseEntity<Header> getStudyAllList(@AuthenticationPrincipal User user){
        List<Study> studyAllList = studyService.getAllList();
        return new ResponseEntity(Header.OK(studyAllList),HttpStatus.OK);
    }
}