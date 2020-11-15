package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.StudyMember;
import com.bootproj.pmcweb.Network.Header;
import com.bootproj.pmcweb.Service.StudyMemberService;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;


@Slf4j
@RequestMapping("/studyMember")
@RestController
@RequiredArgsConstructor
public class StudyMemberController {

//    private final StudyService studyService;

    private final StudyMemberService studyMemberService;

    // 스터디 참여
    @PostMapping
    public ResponseEntity<Header> joinStudy(@ModelAttribute StudyMember studyMember){
        Long id = studyMemberService.joinStudy(studyMember);
        HashMap<String, Long> resultMap = new HashMap<>();
        resultMap.put("memberId", id);
        log.info("result > ", resultMap);
        return new ResponseEntity(Header.OK(resultMap), HttpStatus.CREATED);
    }

    // 스터디 참여요청 수락 거절
    @PutMapping("/status/{studyId}")
    public HashMap changeMemberStatus(@PathVariable(value = "studyId")Long studyId, @RequestParam(value = "memberId")Long memberId){
        return null;
    }
}
