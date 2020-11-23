package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Region;
import com.bootproj.pmcweb.Domain.Subject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    public List<Region> selectAllRegions();
    public List<Subject> selectAllSubjects();
}
