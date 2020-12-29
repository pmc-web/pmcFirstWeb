package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Dates;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DateMapperTest {

    @Autowired
    DateMapper dateMapper;

    private Date date = new Date();
    private String description = "오늘 스터디 할 일";
    private Long studyId = 3L;
    private String updateDescription = "오늘 스터디 할 일 수정";

    private Long datesId = 1L;

    @Test
    void createDate() {
        Dates dates = new Dates().builder().date(date).studyId(studyId).description(description).build();
        Integer result = dateMapper.createDate(dates);
        log.info("create id : {}", dates.getId());
        Assertions.assertThat(result.equals(1));
    }

    @Test
    void getDate() {
        Dates dates = dateMapper.getDate(datesId);
        log.info("get Dates test : {}", dates.toString());
        Assertions.assertThat(dates.getId().equals(datesId));
    }

    @Test
    void updateDate(){
        Dates dates = new Dates().builder().id(datesId).description(updateDescription).date(new Date()).build();
        Integer result = dateMapper.updateDate(dates);
        Assertions.assertThat(result.equals(1));
    }

    @Test
    void deleteDate(){
        Long id = 4L;
        Integer result = dateMapper.deleteDate(id);
        Assertions.assertThat(result.equals(1));
    }
}