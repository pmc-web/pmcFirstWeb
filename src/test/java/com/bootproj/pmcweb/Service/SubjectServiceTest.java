package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Config.DatabaseConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {SubjectServiceImpl.class, DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubjectServiceTest {

    @Autowired
    private SubjectServiceImpl subjectService;

    @Test
    void getSubjectDepth1(){
        List<String> result = subjectService.getSubjectDepth1();
        Assertions.assertThat(result.size()>0);
    }

    @Test
    void getSubjectDepth2(){
        String subjectDepth1 = "운동";
        List<Map> result = subjectService.getSubjectDepth2(subjectDepth1);
        Assertions.assertThat(result.size()>0);
    }


}
