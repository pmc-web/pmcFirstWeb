package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Common.Request.StudyCreateRequest;
import com.bootproj.pmcweb.Common.Response.StudyApiResponse;
import com.bootproj.pmcweb.Domain.Study;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudyMapper {
    /**
     * made by songi
     * */
    public List<Study> getStudyList(@Param("limit") Integer limit, @Param("offset") Integer offset); // TODO : mybatis 문법 잘 아시는분 리팩토링 좀!
    public List<Study> getStudyListByDate(String from, String to); // TODO : 날짜
    public Integer insertStudy(Study study);
    public Study getStudyDetail(Long studyId);
    public StudyApiResponse getStudyInfoDetail(Long studyId);
    public void putStudyStatus(@Param("id")Long studyId, @Param("status")String status);

    List<String> getStudyAllList();
}
