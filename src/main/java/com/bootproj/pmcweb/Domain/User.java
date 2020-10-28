package com.bootproj.pmcweb.Domain;

import lombok.*;
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

    public User(Long id, String email, String password, String status, String name, String role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.instTime = new Date(System.currentTimeMillis());
        this.status = status;
        this.name = name;
        this.role = role;
    }
}
