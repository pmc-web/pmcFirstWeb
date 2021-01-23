package com.bootproj.pmcweb.Domain;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
public class PersistentLogins {
    // create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, "
    //			+ "token varchar(64) not null, last_used timestamp not null
    private String series;
    private String username;
    private String token;
    private LocalDateTime lastUsed;
}
