package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Study;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyMapper {
    public List<Study> getStudyList();
}
