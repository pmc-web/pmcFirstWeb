package com.bootproj.pmcweb.Network.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "이메일 발송 실패")
public class SendEmailException extends Exception {
    public SendEmailException(String message) {
        super(message);
    }
}
