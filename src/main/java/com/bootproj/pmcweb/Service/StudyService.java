package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Study;

import java.util.List;

public interface StudyService {
    public List<Study> selectStudyList();
    //스터디 생성 by songi
    public Long createStudy(Study study);
    //스터디 디테일 보기 by songi
    public Study getStudyDetail(Long studyId);
    //스터디 참여하기 by songi
    public Study joinStudy(Long studyId, Long userId);
    //스터디 모집 마감
    public Study putStudyStatus(Long studyId);
    //스터디 삭제
    public Integer removeStudy(Long studyId);
}
