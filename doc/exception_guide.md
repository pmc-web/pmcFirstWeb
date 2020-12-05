# 예외 처리 가이드

해당 문서는 Spring 프로젝트 팀원들과 Exception 처리 로직을 공통화 하기 위해 작성된 가이드입니다. 예외 처리 시 해야 할 사항은 다음과 같습니다.

1.  ResultCode 작성
2.  Exception 파일 생성 (혹은 기존 파일 사용)
3.  GlobalExceptionHandler에 ExceptionHandler 추가

---

## 1\. ResultCode 작성

아래 ResultCode에 어떤 에러/결과인지 추가합니다. (Network/ResultCode.java) 해당 Status와 Code, Message 내용은 ResponseEntity<>내에 포함되어 서비스 결과를 반환할 때 사용됩니다. 이후 ResultCode가 늘어 한 파일에서 관리하기 어려울 경우 각 모듈 별로 코드를 따로 관리할 수 있습니다.

-   첫번째 파라미터: HTTP Status Code
-   두번쨰 파라미터: Code
    -   모듈의 앞글자를 따고 뒤에 번호를 붙입니다.
    -   (예) U0001
-   세번째 파라미터: Message
    -   Response message에 포함될 내용입니다. 결과에 대한 내용을 반환합니다.

HTTP Status에 관한 문서는 [링크](https://httpstatuses.com/)를 참고하여 넣어주시길 바랍니다.

```
public enum ResultCode {
    // Common
    OK(HttpStatus.OK, "C0001","OK"),
    OK_CREATED(HttpStatus.CREATED, "C0002", "성공적으로 생성되었습니다"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "C0004", "해당하는 값이 존재하지 않습니다."),

    REQUEST_ERROR_INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "R0001", "잘못된 요청입니다."),
    REQUEST_ERROR_FORBIDDEN(HttpStatus.FORBIDDEN, "R0003", "권한이 없는 사용자 입니다."),
    REQUEST_ERROR_METHOD_NOT_ALLOWED(HttpStatus.NOT_ACCEPTABLE, "R0004", "허용되지 않은 요청입니다."),

    // User
    ERROR_EMAIL_DUPLICATE(HttpStatus.INTERNAL_SERVER_ERROR, "U0001", "이메일이 중복되었습니다."),
    ERROR_USER_NOT_FOUND(HttpStatus.NO_CONTENT, "U0002", "해당하는 유저가 존재하지 않습니다."),
    ...

    ;

    private HttpStatus status;
    private String code;
    private String message;
    ...
}
```

---

## 2\. Exception 파일 생성

SpringFramework에서 지원하지 않는 새로운 Exception을 추가하는 경우 Network/Exception 폴더 내에 Exception Class를 새로 정의합니다. 예시 파일은 아래와 같습니다.

```
package com.bootproj.pmcweb.Common.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "중복된 이메일")
public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
```

---

## 3\. GlobalExceptionHandler에 해당 ExceptionHandler 추가

GlobalExceptionHandler은 예외를 한 곳에서 처리하기 위해 만들어진 ControllerAdvice 어노테이션이 달려있는 클래스입니다. Common에서 관리해야하는 Exception, 각 모듈에서 사용하는 Exception들에 대한 핸들링을 모두 여기서 처리합니다. 추가할 때는 아래와 같이 추가합니다.

```
    /**
     * 유저 생성 시 이메일이 중복된 경우 발생
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Header> handleDuplicateEmailException(DuplicateEmailException e) {
        log.error("DuplicateEmailException");
        return new ResponseEntity(Header.Error(ResultCode.ERROR_EMAIL_DUPLICATE), ResultCode.ERROR_EMAIL_DUPLICATE.getStatus());
    }
```

위 예시와 같이 어떤 Exception인지 @ExceptionHandler에 정의하고, Exception 발생 시 해야 할 추가적인 로직이 있다면 log아래에 작성해줍니다. Exception 발생 시 위와 같이 전달해주면 ResultCode내의 status, code, message를 전달해주게 됩니다.

---

## Api Response 결과 예시

예시에 대한 Exception이 발생했을 경우, Response 결과 값은 아래와 같습니다. ResultCode에서 정의한 message값은 UI에서 어떤 문제가 발생했는지 사용자에게 보여줄 수도 있는 정보임에 주의합니다.

```
{
    "transaction_time":"2020-11-06T18:48:58.5844357",
    "result_code":"U0001",
    "description":"이메일이 중복되었습니다."
}
```

---

## Header 파일에 대한 추가 설명

Network에서 사용하는 Header의 경우, api 통신시간, 응답 코드, api 부가 설명을 담은 내용을 포함하여 객체를 전달해주게 됩니다.

헤더파일은 정상적으로 처리되고 처리 결과를 반환해야 할 경우  
`Header.OK(data)`를 사용하며 (get함수나 created된 정보를 반환해야 할 경우), 반환해야 하는 데이터가 없을 경우 `Header.OK()`만 사용하면 됩니다. 만약 위에서 정의한 ResultCode를 포함하여 전달하고 싶을 경우 `Header.OK(resultCode, data)`를 사용합니다.

에러일 경우도 어떤 에러인지 ResulrCode에 넣어 `Header.ERROR(resultCode)`를 호출하여 사용하며, HTTP Status를 200이 아니라 다른 값 (400등)으로 반환해야 할 경우 `ResponseEntity(Header.Error(ResultCode.ERROR_EMAIL_DUPLICATE), ResultCode.ERROR_EMAIL_DUPLICATE.getStatus());`와 같이 사용합니다.

```
    // OK , Data (200)
    public static <T> Header<T> OK(T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(ResultCode.OK.getCode())
                .description(ResultCode.OK.getMessage())
                .data(data)
                .build();
    }
```

추가 및 수정이 필요한 사항이 있다면 언제든지 말씀 부탁드립니다.