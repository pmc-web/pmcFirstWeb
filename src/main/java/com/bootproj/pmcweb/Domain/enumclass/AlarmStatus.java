package com.bootproj.pmcweb.Domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AlarmStatus {
    NOT_READ(0, "NOT_READ", "아직 알람을 읽지 않은 상태"),
    READ(1, "READ", "알람을 읽은 상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
