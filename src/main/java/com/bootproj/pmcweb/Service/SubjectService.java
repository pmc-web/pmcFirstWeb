package com.bootproj.pmcweb.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SubjectService {
    public List<String> getSubjectDepth1();
    public List<Map> getSubjectDepth2(String subjectDepth1);
}
