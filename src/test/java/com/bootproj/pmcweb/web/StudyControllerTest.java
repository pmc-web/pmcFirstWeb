package com.bootproj.pmcweb.web;

import com.bootproj.pmcweb.Controller.StudyController;
import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Service.StudyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StudyController.class) // 테스트 대상 controller 선언
public class StudyControllerTest {

    //TODO : controller test code 작성방법 공부하기
    @Autowired
    private MockMvc mvc;

    @MockBean
    private StudyService studyService;

    Date startDate = new SimpleDateFormat("yyyy-mm-dd").parse("2020-10-11");
    Date endDate = new SimpleDateFormat("yyyy-mm-dd").parse("2021-04-10");
//    final Study study = Study.builder()
//            .title("title test !! ")
//            .description("스터디 생성 하기 controller. ")
//            .startDate(startDate)
//            .endDate(endDate)
//            .regionId(1L)
//            .subjectId(1L)
//            .build();


    public StudyControllerTest() throws ParseException {
    }

    @Test
    public void testMakeStudy() throws Exception {
//        this.mvc.perform(post("/study")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().string("test ")) .andDo(print());
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
        Study test = new Study();
        //when
//        final ResultActions actions = mvc.perform(post("/study").param());
        //mvc.perform(post("/study")).andExpect(status().isOk());
        //.andExpect(content().string(study));
    }
}
