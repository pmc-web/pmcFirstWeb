package com.bootproj.pmcweb.Common.Request;

import com.bootproj.pmcweb.Domain.enumclass.StudyStatus;
import com.bootproj.pmcweb.Domain.enumclass.StudyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class StudyCreateRequest {
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date instTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updtTime;
    private String status = StudyStatus.OPEN.getTitle();
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("startDate")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date startDate;
    @JsonProperty("endDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date endDate;
    private Integer evaluation = 0;
    private String type = StudyType.FREE.getTitle();
    @JsonProperty("subjectId")
    private Long subjectId;
    @JsonProperty("regionId")
    private Long regionId;
}
