package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyMapper {
    /**
     * made by songi
     * */
    public List<Study> getStudyList(@Param("limit") Integer limit, @Param("offset") Integer offset);
    public Integer insertStudy(Study study);
    public Study getStudyDetail(Long studyId);
    public void putStudyStatus(@Param("id")Long studyId, @Param("status")String status);
}
