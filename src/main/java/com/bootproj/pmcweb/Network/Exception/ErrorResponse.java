package com.bootproj.pmcweb.Network.Exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private FieldError errors;
    private String code;

    @Getter
    @NoArgsConstructor
    public static class FieldError{
        private String field;
        private String value;
        private String reason;
    }
}
