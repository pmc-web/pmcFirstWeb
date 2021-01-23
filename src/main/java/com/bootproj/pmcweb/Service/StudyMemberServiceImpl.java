package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Common.Response.StudyMemberApiResponse;
import com.bootproj.pmcweb.Domain.StudyMember;
import com.bootproj.pmcweb.Mapper.StudyMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudyMemberServiceImpl implements StudyMemberService {
    final private StudyMemberMapper studyMemberMapper;

    @Override
    public Long joinStudy(StudyMember studyMember) {
        Long result = studyMemberMapper.insertStudyMember(studyMember);
        if(result == 1){
            return studyMember.getId();
        }else {
            return 0L;
        }
    }

    @Override
    public void changeStatus(Long studyMemberId, String status) {
        studyMemberMapper.changeRole(studyMemberId, status);
    }

    @Override
    public String getMemberRole(Long studyId, Long userId) {
        return studyMemberMapper.getMemberRole(studyId, userId);
    }

    @Override
    public List<StudyMemberApiResponse> getStudyMembers(Long studyId) {
        return studyMemberMapper.selectStudyMembers(studyId);
    }
}
