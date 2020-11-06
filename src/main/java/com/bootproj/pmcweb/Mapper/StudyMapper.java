package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyMapper {
    // 조회
    public List<Study> getStudyList();
    // 생성 by songi
    public Integer insertStudy(Study study);
    // 스터디 detail by songi
    public Study getStudyDetail(Long studyId);
    // 스터디 참여 by songi
    public Study joinStudy(Long studyId, Long userId);
    // 스터디 상태변경 by songi
    public Study putStudyStatus(Long studyId);
}
