package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudyMapper {
    public List<Study> getStudyList();
}
