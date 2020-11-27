package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Config.DatabaseConfiguration;
import com.bootproj.pmcweb.Domain.Region;
import com.bootproj.pmcweb.Domain.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {RegionServiceImpl.class, DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RegionServiceTest {

    @Autowired
    private RegionServiceImpl regionService;

    @Test
    void getAllRegions() {

    }

    @Test
    void getAllSubjects() {
    }
}