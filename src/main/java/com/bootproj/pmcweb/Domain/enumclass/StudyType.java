package com.bootproj.pmcweb.Domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudyType {
    FREE(0, "FREE", "무료 스터디"),
    PAY(1, "PAY", "유료 스터디");

    private Integer id;
    private String title;
    private String description;
}
