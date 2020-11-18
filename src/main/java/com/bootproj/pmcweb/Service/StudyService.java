package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Study;

import java.util.List;

public interface StudyService {

    public List<Study> getStudyList(Integer page);
    public Long createStudy(Study study);
    public Study getStudyDetail(Long studyId);
    public Study putStudyStatus(Long studyId, String status);

}
