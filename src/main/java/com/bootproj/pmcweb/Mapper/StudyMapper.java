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
    public Integer getCreateStudyId();
}
