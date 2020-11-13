package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
=======
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> efdec49... [feat][Study] studymember insert + test
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

<<<<<<< HEAD
=======
@Slf4j
>>>>>>> efdec49... [feat][Study] studymember insert + test
@RequestMapping("/studyMember")
@RestController
@RequiredArgsConstructor
public class StudyMemberController {

    private final StudyService studyService;

    // 스터디 참여
    @PostMapping
<<<<<<< HEAD
    public Study joinStudy(@PathVariable(value = "studyId")Long studyId, @RequestParam(value = "userId")Long userId){
        return studyService.joinStudy(studyId, userId);
=======
    public ResponseEntity<Header> joinStudy(@ModelAttribute StudyMember studyMember){
        Long id = studyMemberService.joinStudy(studyMember);
        HashMap<String, Long> resultMap = new HashMap<>();
        resultMap.put("memberId", id);
        log.info("result > ", resultMap);
        return new ResponseEntity(Header.OK(resultMap), HttpStatus.CREATED);
>>>>>>> efdec49... [feat][Study] studymember insert + test
    }

    // 스터디 참여요청 수락 거절
    @PutMapping("/status/{studyId}")
    public HashMap changeMemberStatus(@PathVariable(value = "studyId")Long studyId, @RequestParam(value = "memberId")Long memberId){
        return null;
    }
}
