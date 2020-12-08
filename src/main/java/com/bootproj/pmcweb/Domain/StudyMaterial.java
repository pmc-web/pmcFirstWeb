package com.bootproj.pmcweb.Domain;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StudyMaterial {
    private Long id;
    private Long attachmentId;
    private Long studyId;
    private String type;
}
