package com.bootproj.pmcweb.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StudyDto {
    private String title;
    private String description;

    @Builder
    public StudyDto(String title, String description){
        this.title = title;
        this.description = description;

    }
}
