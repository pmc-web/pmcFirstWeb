package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Common.Response.StudyMemberApiResponse;
import com.bootproj.pmcweb.Domain.StudyMember;

import java.util.List;

public interface StudyMemberService {
    /**
     * made by songi
     * */
    public Long joinStudy(StudyMember studyMember);
    public void changeStatus(Long studyMemberId, String status);
    public String getMemberRole(Long studyId, Long userId);
    public List<StudyMemberApiResponse> getStudyMembers(Long studyId);
}
