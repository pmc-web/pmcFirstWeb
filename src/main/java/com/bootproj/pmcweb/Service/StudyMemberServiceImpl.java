package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.StudyMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // 생성자를 통해 DI
@Service
public class StudyMemberServiceImpl implements StudyMemberService {

    @Override
    public StudyMember joinStudy(Long studyId, Long userId) {
        return null;
    }
}
