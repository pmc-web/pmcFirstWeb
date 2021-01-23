package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Common.Request.StudyCreateRequest;
import com.bootproj.pmcweb.Common.Response.StudyApiResponse;
import com.bootproj.pmcweb.Domain.Study;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface StudyService {

    public List<Study> getStudyList(Integer page);
    public Study createStudy(Study study);
    public Optional<Study> getStudyDetail(Long studyId);
    public Optional<StudyApiResponse> getStudyInfo(Long studyId);
    public Optional<Study> putStudyStatus(Long studyId, String status);
    List<Study> getAllList();

    List<Study> getSearchList(String word);
}
