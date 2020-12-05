package com.bootproj.pmcweb.Common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "잘못된 패스워드")
public class PasswordNotMatchException extends Exception{
    public PasswordNotMatchException(String message) {
        super(message);
    }
}
