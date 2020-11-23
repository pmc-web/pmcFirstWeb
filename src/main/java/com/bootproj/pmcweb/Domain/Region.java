package com.bootproj.pmcweb.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Region {
    private Long id;
    private String regionDepth1;
    private String regionDepth2;
    private String regionDepth3;
}
