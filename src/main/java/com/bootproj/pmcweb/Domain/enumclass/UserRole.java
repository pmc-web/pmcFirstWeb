package com.bootproj.pmcweb.Domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN(0, "ADMIN", "관리자"),
    NORMAL(1, "NORMAL", "일반 유저")
    ;

    private Integer id;
    private String title;
    private String description;
}
