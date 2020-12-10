package com.bootproj.pmcweb.Service;

import com.bootproj.pmcweb.Domain.Alarm;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;
import com.bootproj.pmcweb.Domain.enumclass.AlarmType;
import com.bootproj.pmcweb.Mapper.AlarmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmMapper alarmMapper;

    @Override
    public Alarm insert(Alarm alarm) {
        alarm.setType(AlarmType.STUDY);
        alarm.setStatus(AlarmStatus.NOT_READ);
        alarm.setAlarmTime(new Date(System.currentTimeMillis()));
        alarmMapper.createAlarm(alarm);
        return alarm;
    }

    @Override
    public List<Alarm> getNotReadListByUserId(Long userId) {
        List<Alarm> alarms = alarmMapper.getAlarmByUserIdStatus(userId, AlarmStatus.NOT_READ);
        return alarms;
    }

    @Override
    public void updateStatusById(Long id, AlarmStatus status) {
        alarmMapper.updateAlarmStatus(id, status);
    }
}
