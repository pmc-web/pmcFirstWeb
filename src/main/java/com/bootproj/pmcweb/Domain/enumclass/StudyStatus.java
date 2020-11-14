package com.bootproj.pmcweb.Domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudyStatus {
    OPEN(0, "OPEN", "스터디 모집"),
    CLOSE(1, "CLOSE", "스터디 마감"),
    DELETE(2, "DELETE", "스터디 삭제");

    private Integer id;
    private String title;
    private String description;
}
