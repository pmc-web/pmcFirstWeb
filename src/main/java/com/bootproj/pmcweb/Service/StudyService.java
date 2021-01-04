package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Common.Request.StudyCreateRequest;
import com.bootproj.pmcweb.Common.Response.StudyApiResponse;
import com.bootproj.pmcweb.Domain.Study;

import java.util.List;
import java.util.Map;

public interface StudyService {

    public List<Study> getStudyList(Integer page);
    public Long createStudy(Study study);
    public Study getStudyDetail(Long studyId);
    public StudyApiResponse getStudyInfo(Long studyId);
    public Study putStudyStatus(Long studyId, String status);


    List<String> getAllList();
}
