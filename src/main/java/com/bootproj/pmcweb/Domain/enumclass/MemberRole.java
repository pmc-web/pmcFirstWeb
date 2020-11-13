package com.bootproj.pmcweb.Domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberRole {
    ADMIN(0, "ADMIN", "스터디장"),
    NORMAL(1, "NORMAL", "스터디원"),
    GUEST(2, "GUEST", "가입 요청중")
    ;

    private Integer id;
    private String title;
    private String description;
}
