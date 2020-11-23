package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Region;
import com.bootproj.pmcweb.Domain.Subject;
import com.bootproj.pmcweb.Mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    @Override
    public List<Region> getAllRegions() {
        return categoryMapper.selectAllRegions();
    }

    @Override
    public List<Subject> getAllSubjects() {
        return categoryMapper.selectAllSubjects();
    }
}
