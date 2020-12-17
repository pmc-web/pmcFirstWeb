package com.bootproj.pmcweb.Domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmType {
    STUDY(0, "STUDY", "스터디 모임 일정 알람"),
    MESSAGE(1, "MESSAGE", "메세지 알람")
    ;

    private int id;
    private String title;
    private String description;
}
