package com.bootproj.pmcweb.Common.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateApiRequest {
    @JsonProperty("subjectId")
    private Long subjectId;
    @JsonProperty("regionId")
    private Long regionId;
}
