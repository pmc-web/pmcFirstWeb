package com.bootproj.pmcweb.Domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Attachment {
    private Long id;
    private String path;
    private String name;
    private Date instTime;
    private Date updtTime;
}
