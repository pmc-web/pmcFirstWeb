package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Config.DatabaseConfiguration;
import com.bootproj.pmcweb.Domain.Region;
import com.bootproj.pmcweb.Domain.Subject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CategoryServiceImpl.class, DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void getAllRegions() {
        List<Region> regions = categoryService.getAllRegions();
        assertThat(regions.size()>0);
    }

    @Test
    void getAllSubjects() {
        List<Subject> subjects = categoryService.getAllSubjects();
        assertThat(subjects.size()>0);
    }
}