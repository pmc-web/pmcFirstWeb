package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Common.Request.StudyCreateRequest;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.enumclass.StudyStatus;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudyServiceTest {
    @Autowired
    private StudyServiceImpl studyServiceImpl;

    @Test
    void getAllList(){
        List<Study> list = studyServiceImpl.getStudyList(1);
        assertThat(list.size()>0);
    }

    @Test
    void createStudy() throws ParseException {
        Study testStudy = new Study();
        String title = "study mapper test study";
        String description = "this is description";
        Date startDate = new SimpleDateFormat("yyyy-mm-dd").parse("2020-10-11");
        Date endDate = new SimpleDateFormat("yyyy-mm-dd").parse("2021-04-10");
        Long subjectId = 1L;
        Long regionId = 1L;
        testStudy.setTitle(title);
        testStudy.setDescription(description);
        testStudy.setStartDate(startDate);
        testStudy.setEndDate(endDate);
        testStudy.setSubjectId(subjectId);
        testStudy.setRegionId(regionId);

        studyServiceImpl.createStudy(testStudy);
        assertThat(testStudy.getId()!=null);

    }

    @Test
    void getStudyDetail(){
        Long findId = 3L;
        Optional<Study> test = studyServiceImpl.getStudyDetail(findId);
        assertThat(test.get().getId().equals(findId));
    }

    @Test
    void putStudyStatus(){
        Long studyId = 3L;
        String del = StudyStatus.DELETE.getTitle();
        String open = StudyStatus.OPEN.getTitle();
        String close = StudyStatus.CLOSE.getTitle();

        Optional<Study> test = studyServiceImpl.putStudyStatus(studyId, open);
        System.out.print(test);
        assertThat(test.equals(test));
    }
}
