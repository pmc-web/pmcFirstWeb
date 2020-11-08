package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyMemberMapper {
    // 스터디 참여 by songi
    public Study joinStudy(Long studyId, Long userId);
}
