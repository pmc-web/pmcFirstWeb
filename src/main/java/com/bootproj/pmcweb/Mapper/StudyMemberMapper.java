package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.StudyMember;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMemberMapper {
    /**
     * made by songi
     * */
    public Long insertStudyMember(StudyMember member); // 스터디멤버에 추가
    public StudyMember changeRole(Long id, String role); // 역할 변경
}
