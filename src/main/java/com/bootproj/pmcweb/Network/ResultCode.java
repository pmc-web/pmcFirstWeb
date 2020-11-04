package com.bootproj.pmcweb.Network;

public enum ResultCode {
    // Common
    OK(200, "C0001","OK"),
    OK_CREATED(201, "C0002", "성공적으로 생성되었습니다"),
    NO_CONTENT(204, "C0004", "해당하는 값이 존재하지 않습니다."),

    // Request Error
    REQUEST_ERROR_INVALID_INPUT_VALUE(400, "R0001", "잘못된 요청입니다."),
    REQUEST_ERROR_FORBIDDEN(403, "R0003", "권한이 없는 사용자 입니다."),
    REQUEST_ERROR_METHOD_NOT_ALLOWED(405, "R0004", "허용되지 않은 요청입니다."),

    ERROR(500, "E0001", "Error"),

    // User
    ERROR_EMAIL_DUPLICATE(500, "U0001", "이메일이 중복되었습니다."),
    ERROR_USER_NOT_FOUND(204, "U0002", "해당하는 유저가 존재하지 않습니다."),
    ERROR_SEND_EMAIL(500, "U0003", "이메일을 전송하는데 실패했습니다."),
    ERROR_INSERT_USER(500, "U0004", "회원정보를 저장하는데 실패했습니다.")

    ;

    private int status;
    private String code;
    private String message;


    ResultCode(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}