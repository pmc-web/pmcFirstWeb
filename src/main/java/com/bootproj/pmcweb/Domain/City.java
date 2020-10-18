package com.bootproj.pmcweb.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class City {
    private Long id;
    private String name;
    private String country;
    private String population;
}
