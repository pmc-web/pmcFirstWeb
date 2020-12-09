package com.bootproj.pmcweb.Common.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {
    private long id;
    private String account;
    private String status;
    private String email;
    private String role;
    private Date instTime;
}
