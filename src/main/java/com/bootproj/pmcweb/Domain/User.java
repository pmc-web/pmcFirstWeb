package com.bootproj.pmcweb.Domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
//@Data
public class User {
    private Long id;
    private String email;
    private String password;
    private Date instTime;
    private Date updtTime;
    private String status;
    private String name;
    private String role;
    private Long regionId;
    private Long attachmentId;
}
