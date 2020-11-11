package com.bootproj.pmcweb.Domain;

import com.bootproj.pmcweb.Domain.enumclass.UserRole;
import com.bootproj.pmcweb.Domain.enumclass.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    private Long id;
    private String email;
    private String password;
    private Date instTime;
    private Date updtTime;
    private int grade;
    private String status;
    private String name;
    private String role;
    private Long regionId;
    private Long attachmentId;
    private String authKey;
}
