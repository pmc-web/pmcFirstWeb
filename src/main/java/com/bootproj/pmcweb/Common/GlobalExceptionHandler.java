package com.bootproj.pmcweb.Common;

import com.bootproj.pmcweb.Common.Exception.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.net.BindException;

@ControllerAdvice // 예외를 한곳에서 처리하기 위한 어노테이션
@Log4j2
public class GlobalExceptionHandler {

    // -----------------------------Common-----------------------------------
    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Header> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.REQUEST_ERROR_INVALID_INPUT_VALUE), ResultCode.REQUEST_ERROR_INVALID_INPUT_VALUE.getStatus());
    }

    /**
     * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Header> handleBindException(BindException e) {
        log.error("BindException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.REQUEST_ERROR_INVALID_INPUT_VALUE), ResultCode.REQUEST_ERROR_INVALID_INPUT_VALUE.getStatus());
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Header> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.REQUEST_ERROR_INVALID_INPUT_VALUE), ResultCode.REQUEST_ERROR_INVALID_INPUT_VALUE.getStatus());
    }

    // -------------------------------------User------------------------------------------

    /**
     * 유저 생성 시 이메일이 중복된 경우 발생
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Header> handleDuplicateEmailException(DuplicateEmailException e) {
        log.error("DuplicateEmailException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.ERROR_EMAIL_DUPLICATE), ResultCode.ERROR_EMAIL_DUPLICATE.getStatus());
    }

    /**
     * 유저 가입 이메일 발송 시 어드민 계정 등의 문제로 이메일 전송에 실패했을 경우 발생
     */
    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<Header> handlerSendEmailException(SendEmailException e) {
        log.error("SendEmailException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.ERROR_SEND_EMAIL), ResultCode.ERROR_SEND_EMAIL.getStatus());
    }

    /**
     * 유저 이메일이나 아이디로 검색했을 때 존재하지 않는 경우 발생
     */
    @ExceptionHandler(NoMatchingAcountException.class)
    public ResponseEntity<Header> handlerNoMatchingAcountException(NoMatchingAcountException e){
        log.error("NoMatchingAcountException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.ERROR_USER_NOT_FOUND), ResultCode.ERROR_USER_NOT_FOUND.getStatus());
    }

    /**
     * 유저 패스워드 변경 시 패스워드가 잘못된 경우 발생
     */
    @ExceptionHandler(PasswordNotMatchException.class)
    public ResponseEntity<Header> handlerPasswordNotMatchException(PasswordNotMatchException e){
        log.error("PasswordNotMatchException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.ERROR_INVALID_PASSWORD), ResultCode.ERROR_INVALID_PASSWORD.getStatus());
    }
    /**
     * 회원가입시 이메일 인증하지 않은 경우 발생
     */
    @ExceptionHandler(NoAuthEmailException.class)
    public ResponseEntity<Header> handlerNoAuthEmailException(NoAuthEmailException e){
        log.error("NoAuthEmailException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.ERROR_INVALID_PASSWORD), ResultCode.ERROR_INVALID_PASSWORD.getStatus());
    }


    // ------------------------------------- Attachment ------------------------------------------

    /**
     * 파일 저장 시 문제가 발생했을 경우 발생 (프로필 사진 저장 등)
     */
    @ExceptionHandler(FileSaveException.class)
    public ResponseEntity<Header> handlerFileSaveException(FileSaveException e){
        log.error("FileSaveException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.ERROR_SAVE_FILE), ResultCode.ERROR_SAVE_FILE.getStatus());
    }

    /**
     * 파일 삭제 시 문제가 발생했을 경우 발생 (프로필 사진 저장 등)
     */
    @ExceptionHandler(FileDeleteException.class)
    public ResponseEntity<Header> handlerFileSaveException(FileDeleteException e){
        log.error("FileDeleteException");
        log.error(e.getMessage());
        return new ResponseEntity(Header.Error(ResultCode.ERROR_SAVE_FILE), ResultCode.ERROR_SAVE_FILE.getStatus());
    }
}
