package com.bootproj.pmcweb.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Study{
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date instTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updtTime;

    private Integer status; // String -> Integer default:0 (스터디진행중)
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private Integer evaluation;
    private Integer type; // String -> Integer (무료-0, 유료-1)
    private Long subjectId;
    private Long regionId;
}
