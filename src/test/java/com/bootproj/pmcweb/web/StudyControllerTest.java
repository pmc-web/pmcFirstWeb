package com.bootproj.pmcweb.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = StudyController.class)
public class StudyControllerTest {

//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void test_studyController() throws Exception{
//        String title = "스터디 생성 테스트";
//        String description = "스터디스터디";
//        Date start_date = new Date();
//        Date end_date = new Date();
//        Integer evaluation = 0;
//        Integer type = 0;
//        Integer region_id = 1;
//        Integer subject_id = 2;
//
//        mvc.perform(post("/study")).andExpect(status().isOk());
//        //.andExpect(content().string(study));
//    }
}
