package com.bootproj.pmcweb.Controller;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping("/studyMember")
@RestController
@RequiredArgsConstructor
public class StudyMemberController {

    private final StudyService studyService;

    // 스터디 참여
    @PostMapping
    public Study joinStudy(@PathVariable(value = "studyId")Long studyId, @RequestParam(value = "userId")Long userId){
        return studyService.joinStudy(studyId, userId);
    }

    // 스터디 참여요청 수락 거절
    @PutMapping("/status/{studyId}")
    public HashMap changeMemberStatus(@PathVariable(value = "studyId")Long studyId, @RequestParam(value = "memberId")Long memberId){
        return null;
    }
}
