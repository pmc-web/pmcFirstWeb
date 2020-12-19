package com.bootproj.pmcweb.Common.Response;

import com.bootproj.pmcweb.Domain.Study;
import com.bootproj.pmcweb.Domain.enumclass.StudyStatus;
import com.bootproj.pmcweb.Domain.enumclass.StudyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudyApiResponse {
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date instTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updtTime;
    private String status = StudyStatus.OPEN.getTitle();
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date endDate;
    private Integer evaluation = 0;
    private String type = StudyType.FREE.getTitle();
    @JsonProperty("subjectId")
    private Long subjectId;
    @JsonProperty("regionId")
    private Long regionId;
    private Long adminId; // 스터디장
    private Long attachmentId;
    private String attachmentPath;
}
