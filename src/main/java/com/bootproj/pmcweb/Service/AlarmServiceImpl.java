package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Common.Response.AlarmApiResponse;
import com.bootproj.pmcweb.Domain.Alarm;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;
import com.bootproj.pmcweb.Domain.enumclass.AlarmType;
import com.bootproj.pmcweb.Mapper.AlarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {

    private final AlarmMapper alarmMapper;

    @Override
    public Alarm insert(Alarm alarm) {
        alarm.setType(AlarmType.STUDY);
        alarm.setStatus(AlarmStatus.NOT_READ);
        alarm.setAlarmTime(new Date(System.currentTimeMillis()));
        alarmMapper.createAlarm(alarm);
        return alarm;
    }

    @Override
    public List<AlarmApiResponse> getNotReadListByUserId(Long userId) {
        List<AlarmApiResponse> alarms = alarmMapper.getAlarmByUserIdStatus(userId, AlarmStatus.NOT_READ);
        return alarms;
    }

    @Override
    public List<AlarmApiResponse> getListByUserId(Long userId) {
        List<AlarmApiResponse> alarms = alarmMapper.getAlarmByUserId(userId);
        return alarms;
    }


    @Override
    public void updateStatusById(Long id, AlarmStatus status) {
        alarmMapper.updateAlarmStatus(id, status);
    }
}
