package com.bootproj.pmcweb.Domain;

import com.bootproj.pmcweb.Domain.enumclass.AlarmStatus;
import com.bootproj.pmcweb.Domain.enumclass.AlarmType;
import lombok.*;

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
    private Date alarmTime;
    private AlarmStatus status;
    private Long userId;
    private Long dateId;
}
