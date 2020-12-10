package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Domain.Alarm;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;
import com.bootproj.pmcweb.Domain.enumclass.AlarmType;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AlarmMapperTest {
    private final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private long testUserId = 99L;
    private long testDateId = 1L;
    private long testId = 2L;

    @Autowired
    AlarmMapper alarmMapper;

    @Test
    void getAlarmByUserId() {
        List<Alarm> alarms = alarmMapper.getAlarmByUserId(testUserId);
        log.info(alarms);
        Assert.assertTrue(alarms.size()>0);
    }

    @Test
    void getAlarmById() {
        Optional<Alarm> optionalAlarm = alarmMapper.getAlarmById(testId);
        log.info(optionalAlarm.orElse(null));
        Assert.assertTrue(optionalAlarm.isPresent());
    }

    @Test
    void createAlarm() {
        Alarm alarm = Alarm.builder()
                .type(AlarmType.STUDY)
                .status(AlarmStatus.NOT_READ)
                .alarmTime(new Date(System.currentTimeMillis()))
                .userId(testUserId)
                .dateId(testDateId)
                .build();

        alarmMapper.createAlarm(alarm);
        log.info(alarm);
        Assert.assertNotNull(alarm.getId());
    }

    @Test
    void updateAlarmStatus() {
        alarmMapper.getAlarmById(testId).ifPresentOrElse(
                (alarm) -> {
                    Assert.assertEquals(AlarmStatus.NOT_READ.getTitle(), alarm.getStatus());
                },
                () -> {
                    createAlarmBeforeTest();
                }
        );
        alarmMapper.updateAlarmStatus(testId, AlarmStatus.READ.getTitle());
        Assert.assertEquals(alarmMapper.getAlarmById(testId).get().getStatus(), AlarmStatus.READ.getTitle());
    }

    @Test
    void deleteAlarmById() {
        alarmMapper.getAlarmById(testId).ifPresentOrElse(
                (alarm) -> {
                    assertEquals(testId, alarm.getId());
                },
                () -> {
                    testId = createAlarmBeforeTest();
                }
        );
        alarmMapper.deleteAlarmById(testId);
        assertTrue(alarmMapper.getAlarmById(testId).isEmpty());
    }

    @Test
    void deleteAlarmByUserId() {
        List<Alarm> alarms = alarmMapper.getAlarmByUserId(testUserId);
        assertTrue(alarms.size()>0);
        alarmMapper.deleteAlarmByUserId(testUserId);
        assertTrue(alarmMapper.getAlarmById(testId).isEmpty());
    }

    @Test
    void deleteAlarmByDateId() {
        alarmMapper.deleteAlarmByDateId(testDateId);
        assertTrue(alarmMapper.getAlarmById(testId).isEmpty());
    }

    private Long createAlarmBeforeTest(){
        Alarm alarm = Alarm.builder()
                .type(AlarmType.STUDY)
                .status(AlarmStatus.NOT_READ)
                .alarmTime(new Date(System.currentTimeMillis()))
                .userId(testUserId)
                .dateId(testDateId)
                .build();

        alarmMapper.createAlarm(alarm);
        return alarm.getId();
    }
}