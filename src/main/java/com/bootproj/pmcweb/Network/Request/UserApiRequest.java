package com.bootproj.pmcweb.Network.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {
    private String account;
    private String password;
    private String email;
}
