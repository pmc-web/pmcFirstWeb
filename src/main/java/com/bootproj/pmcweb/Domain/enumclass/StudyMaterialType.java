package com.bootproj.pmcweb.Domain.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StudyMaterialType {
    MAIN_IMAGE(0, "MAIN_IMAGE", "스터디 메인 이미지"),
    IMAGE(1, "IMAGE", "스터디 이미지 파일"),
    FILE(2, "FILE", "스터디 일반 파일")
    ;

    final private Integer id;
    final private String title;
    final private String description;
}
