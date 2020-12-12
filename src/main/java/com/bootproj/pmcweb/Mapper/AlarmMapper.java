package com.bootproj.pmcweb.Mapper;

import com.bootproj.pmcweb.Common.Response.AlarmApiResponse;
import com.bootproj.pmcweb.Domain.Alarm;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmMapper {
    public List<AlarmApiResponse> getAlarmByUserId(Long userId);

    public Optional<Alarm> getAlarmById (Long id);

    public List<AlarmApiResponse> getAlarmByUserIdStatus(@Param("userId") Long userId, @Param("status") AlarmStatus status);

    public void createAlarm (Alarm alarm);

    public void updateAlarmStatus(@Param("id") Long id, @Param("status") AlarmStatus status);

    public void deleteAlarmById (Long id);

    public void deleteAlarmByUserId (Long userId);

    public void deleteAlarmByDateId (Long dateId);

    // 만약 스터디 호스트가 특정 스터디에 대한 알람을 어떤 유저가 읽었는지 확인하는 기능이 있다면 필요
    // public List<Alarm> getAlarmByDateId (Long dateId);
}
