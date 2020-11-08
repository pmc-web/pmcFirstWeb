package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.StudyMember;

public interface StudyMemberService {
    //스터디 참여하기 by songi
    public StudyMember joinStudy(Long studyId, Long userId); // TODO studymember
}
