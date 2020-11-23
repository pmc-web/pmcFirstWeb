package com.bootproj.pmcweb.Mapper;

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
@SpringBootTest(classes = {DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void selectAllRegion() {
        List<Region> regions = categoryMapper.selectAllRegions();

        assertThat(regions.size()>0);
    }

    @Test
    void selectAllSubject() {
        List<Subject> subjects = categoryMapper.selectAllSubjects();

        assertThat(subjects.size()>0);
    }
}