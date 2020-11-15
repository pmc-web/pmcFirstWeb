package com.bootproj.pmcweb.web;

import com.bootproj.pmcweb.Controller.StudyController;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = StudyController.class)
public class StudyControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private StudyMapper studyMapper;

    @MockBean
    private StudyService studyService;

    @Test
    public void test_sample() throws Exception {
        this.mvc.perform(post("/study"))
                .andExpect(status().isOk());
//                .andExpect();
    }

    @Test
    public void test_studyController() throws Exception{
        //given
        String title = "스터디 생성 테스트";
        String description = "스터디스터디";
        Date start_date = new Date();
        Date end_date = new Date();
        Integer evaluation = 0;
        Integer type = 0;
        Long region_id = 1L;
        Long subject_id = 2L;
        final Study study = new Study(null, title,null,null, 0,description,start_date,end_date,evaluation,type,subject_id,region_id);

        //when
        final ResultActions actions = mvc.perform(post("/study").param(title, title));

        //mvc.perform(post("/study")).andExpect(status().isOk());
        //.andExpect(content().string(study));
    }
}
