package com.bootproj.pmcweb.Network.Exception;

import com.bootproj.pmcweb.Network.Header;
import com.bootproj.pmcweb.Network.ResultCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 예외를 한곳에서 처리하기 위한 어노테이션
@Log4j2
public class GlobalExceptionHandler {

    // Common
    // TODO: 값이 없는 경우 Exception

    // User
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Header> handleDuplicateEmailException(DuplicateEmailException e){
        log.error("DuplicateEmailException");
        return new ResponseEntity(Header.Error(ResultCode.ERROR_EMAIL_DUPLICATE), ResultCode.ERROR_EMAIL_DUPLICATE.getStatus());
    }

    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<Header> handlerSendEmailException(SendEmailException e){
        log.error("SendEmailException");
        return new ResponseEntity(Header.Error(ResultCode.ERROR_SEND_EMAIL), ResultCode.ERROR_SEND_EMAIL.getStatus());
    }
}
