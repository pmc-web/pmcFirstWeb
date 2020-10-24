package com.bootproj.pmcweb.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Study{
    private Long id;
    private String title;
    private Date instTime;
    private Date updtTime;
    private String status;
    private String description;
    private Date startDate;
    private Date endDate;
    private Integer evaluation;
    private String type;
    private Long subjectId;
    private Long regionId;

}
