package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Study;

import java.util.List;

public interface StudyService {
    public List<Study> selectStudyList();
    //스터디 생성 by songi
    public Study createStudy(Study study);
}
