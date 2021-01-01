package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Dates;
import com.bootproj.pmcweb.PmcwebApplication;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PmcwebApplication.class)
class DateServiceImplTest {

    @Autowired
    DateService dateService;

    Dates dates = new Dates().builder().date(new Date()).description(" new study date").studyId(3L).build();

    @Test
    void createDate() {
        Long result = dateService.createDate(dates);
        org.assertj.core.api.Assertions.assertThat(result.equals(dates.getId()));
    }

    @Test
    void getDatesList() {
        List<Dates> list = dateService.getDatesList(3L, "2020", null, null);
        Assertions.assertThat(list.size()>0);
    }

    @Test
    void getDate() {
        Dates dates = dateService.getDateById(1L);
        Assertions.assertThat(dates.getId().equals(1L));
    }

    @Test
    void updateDate(){
        Dates dates = new Dates().builder().id(1L).description("일정 수정합니다").build();
        Dates update = dateService.updateDates(dates);
        Assertions.assertThat(update.getId().equals(dates.getId()));
    }

    @Test
    void deleteDates() {
        Boolean result = dateService.deleteDates(4L);
        Assertions.assertThat(result.equals(false));
    }
}