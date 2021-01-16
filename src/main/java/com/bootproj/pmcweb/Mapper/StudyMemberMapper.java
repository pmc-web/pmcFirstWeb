package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.StudyMember;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyMemberMapper {
    /**
     * made by songi
     * */
    public Long insertStudyMember(StudyMember member); // 스터디멤버에 추가
    public void changeRole(Long id, String role); // 역할 변경
    public StudyMember selectMember(Long id);
    public String getMemberRole(Long studyId, Long userId);
    public List<StudyMember> selectStudyMembers(Long studyId);
}
