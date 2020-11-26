package com.bootproj.pmcweb.Network;

import org.springframework.http.HttpStatus;

public enum ResultCode {
    // Common
    OK(HttpStatus.OK, "C0001","OK"),
    OK_CREATED(HttpStatus.CREATED, "C0002", "성공적으로 생성되었습니다"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "C0004", "해당하는 값이 존재하지 않습니다."),

    // Request Error
    REQUEST_ERROR_INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "R0001", "잘못된 요청입니다."),
    REQUEST_ERROR_FORBIDDEN(HttpStatus.FORBIDDEN, "R0003", "권한이 없는 사용자 입니다."),
    REQUEST_ERROR_METHOD_NOT_ALLOWED(HttpStatus.NOT_ACCEPTABLE, "R0004", "허용되지 않은 요청입니다."),

    // Common Server Error
    ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0001", "서버에 오류가 발생했습니다."),
    ERROR_SAVE_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "E0002", "파일 저장에 실패했습니다."),
    ERROR_DELETE_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "E0003", "파일 삭제에 실패했습니다."),

    // User
    ERROR_EMAIL_DUPLICATE(HttpStatus.INTERNAL_SERVER_ERROR, "U0001", "이메일이 중복되었습니다."),
    ERROR_USER_NOT_FOUND(HttpStatus.NO_CONTENT, "U0002", "해당하는 유저가 존재하지 않습니다."),
    ERROR_SEND_EMAIL(HttpStatus.INTERNAL_SERVER_ERROR, "U0003", "이메일을 전송하는데 실패했습니다."),
    ERROR_INSERT_USER(HttpStatus.INTERNAL_SERVER_ERROR, "U0004", "회원정보를 저장하는데 실패했습니다."),
    ERROR_INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "U0005", "잘못된 패스워드 입니다.")

    ;

    private HttpStatus status;
    private String code;
    private String message;


    ResultCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}