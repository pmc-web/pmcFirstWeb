package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface SubjectMapper {
    public List<String> selectSubjectDepth1();
    public List<Map> selectSubjectDepth2(@Param(value = "subjectDepth1") String subjectDepth1);
    public Optional<Subject> selectSubjectById(Long id);
}
