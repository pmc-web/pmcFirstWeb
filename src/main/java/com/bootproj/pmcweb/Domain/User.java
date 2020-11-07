package com.bootproj.pmcweb.Domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Setter
@Getter
//@Data
@NoArgsConstructor
public class User implements UserDetails {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public String getPassword() {
        return this.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled();
    }

//    public User(String email, String password, String status, String name, String role){
//        PasswordEncoding passwordEncoding = new PasswordEncoding();
//        this.email = email;
//        this.password = passwordEncoding.encode(password);
//        this.instTime = new Date(System.currentTimeMillis());
//        this.status = status;
//        this.name = name;
//        this.role = role;
//    }
}
