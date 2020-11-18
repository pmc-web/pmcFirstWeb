package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Config.DatabaseConfiguration;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.StudyMember;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Study.class, DatabaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudyMemberMapperTest {

    @Autowired
    private StudyMemberMapper studyMemberMapper;

    @Test
    public void insertMember(){
        StudyMember studyMember = new StudyMember();
        studyMember.setStudyRole("GUEST");
        studyMember.setUserId(1L);
        studyMember.setStudyId(4L);

        Long insertId = studyMemberMapper.insertStudyMember(studyMember);

        assertThat(insertId!=0L);
    }

}
