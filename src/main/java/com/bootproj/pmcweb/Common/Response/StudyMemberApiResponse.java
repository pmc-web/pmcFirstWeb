package com.bootproj.pmcweb.Common.Response;

import lombok.Data;

@Data
public class StudyMemberApiResponse {
    private Long id;
    private Long userId;
    private String userName;
    private Long studyId;
    private String studyRole;
}
