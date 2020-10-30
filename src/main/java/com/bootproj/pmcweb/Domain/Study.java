package com.bootproj.pmcweb.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Study{
    private Long id;
    private String title;
    private Date instTime;
    private Date updtTime;
    private Integer status; // String -> Integer default:0 (스터디진행중)
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer evaluation;
    private Integer type; // String -> Integer (무료-0, 유료-1)
    private Long subjectId;
    private Long regionId;
}
