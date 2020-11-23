package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Region;
import com.bootproj.pmcweb.Domain.Subject;

import java.util.List;

public interface CategoryService {
    public List<Region> getAllRegions();
    public List<Subject> getAllSubjects();
}
