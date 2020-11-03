package com.bootproj.pmcweb.Domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    REGISTERED(0, "REGISTERED", "사용자 등록상태"),
    UNREGISTERED(1, "UNREGISTERED", "사용자 해지상태")
    ;

    private Integer id;
    private String title;
    private String description;
}
