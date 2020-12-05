package com.bootproj.pmcweb.Common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_ACCEPTABLE, reason="이메일 인증 되지 않음")
public class NoAuthEmailException extends Exception {
    public NoAuthEmailException(String message) {
        super(message);
    }
}
