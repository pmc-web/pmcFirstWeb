package com.bootproj.pmcweb.Common.Request;

import com.bootproj.pmcweb.Domain.enumclass.StudyStatus;
import com.bootproj.pmcweb.Domain.enumclass.StudyType;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
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
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer evaluation = 0;
    private String type = StudyType.FREE.getTitle();

    private Long subjectId;

    private Long regionId;

    private MultipartFile image;
}
