package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyMapper {
    // 조회
    public List<Study> getStudyList();
    // 생성 by songi
    /**
     * made by songi
     * */
    public List<Study> getStudyList(@Param("limit") Integer limit, @Param("offset") Integer offset); // TODO : mybatis 문법 잘 아시는분 리팩토링 좀!
    public List<Study> getStudyListByDate(String from, String to); // TODO : 날짜
    public Integer insertStudy(Study study);
    // 스터디 detail by songi
    public Study getStudyDetail(Long studyId);
    // 스터디 참여 by songi
    public Study joinStudy(Long studyId, Long userId);
    // 스터디 상태변경 by songi
    public Study putStudyStatus(Long studyId);
}
