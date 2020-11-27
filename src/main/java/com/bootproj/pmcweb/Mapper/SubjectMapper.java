package com.bootproj.pmcweb.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper {
    public List<String> selectSubjectDepth1();
    public List<String> selectSubjectDepth2(@Param(value = "subjectDepth1") String subjectDepth1);
}
