package com.bootproj.pmcweb.Domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Dates {
    Long id;
    Date date;
    String description;
    Long studyId;
}
