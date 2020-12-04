package com.bootproj.pmcweb.Common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "존재하지 않는 계정")
public class NoMatchingAcountException extends Exception {
    public NoMatchingAcountException(String message) {
        super(message);
    }
}
