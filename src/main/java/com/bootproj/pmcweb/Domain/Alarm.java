package com.bootproj.pmcweb.Domain;

import com.bootproj.pmcweb.Common.CommonUtils;
import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;
import com.bootproj.pmcweb.Domain.enumclass.AlarmType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Alarm {
    private Long id;
    private AlarmType type;
    @DateTimeFormat(pattern = CommonUtils.ALARM_DATE_FORMAT)
    private Date alarmTime;
    private AlarmStatus status;
    private Long userId;
    private Long dateId;
}
