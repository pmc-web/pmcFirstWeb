package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Config.DatabaseConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SubjectMapperTest {

    @Autowired
    private SubjectMapper subjectMapper;

    @Test
    void selectSubjectDepth1() {
        List<String> result = subjectMapper.selectSubjectDepth1();
//        Optional<List> depth1 = Optional.of(result);
//        Optional<List> op = Optional.empty();
//        assertThat(depth1).isNotEqualTo(op);
    }

    @Test
    void selectSubjectDepth2() {
        String subjectDepth1 = "프로그래밍";
//        subjectDepth1 = "어학"; //금융, 미디어, 취업, 운동
        List<Map> result = subjectMapper.selectSubjectDepth2(subjectDepth1);
//        Optional<List> depth1 = Optional.of(result);
//        Optional<List> op = Optional.empty();
//        assertThat(depth1).isNotEqualTo(op);
    }
}