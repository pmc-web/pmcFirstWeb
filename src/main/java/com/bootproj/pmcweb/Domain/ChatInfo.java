package com.bootproj.pmcweb.Domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatInfo {
    private String name;
    private String token;
    @Builder
    public ChatInfo(String name, String token){
        this.name = name;
        this.token = token;
    }
}
