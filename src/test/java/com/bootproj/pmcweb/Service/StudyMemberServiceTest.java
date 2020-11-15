package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.StudyMember;
import com.bootproj.pmcweb.PmcwebApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyMemberServiceTest {
    @Autowired
    private StudyMemberService studyMemberService;

    @Test
    void insertMember(){
        StudyMember studyMember = new StudyMember();
        studyMember.setStudyRole("GUEST");
        studyMember.setUserId(1L);
        studyMember.setStudyId(5L);
        Long result = studyMemberService.joinStudy(studyMember);
        assertThat(result!=0L);
    }
}
