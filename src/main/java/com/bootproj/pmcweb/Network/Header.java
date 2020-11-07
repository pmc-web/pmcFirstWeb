package com.bootproj.pmcweb.Network;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.xml.transform.Result;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Header<T> {
    // api 통신 시간
    private LocalDateTime transactionTime;

    // api 응답 코드
    private String resultCode;

    // api 부가 설명
    private String description;

    private T data;

    // OK , No Data (200)
    public static <T> Header<T> OK() {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(ResultCode.OK.getCode())
                .description(ResultCode.OK.getMessage())
                .build();
    }

    // OK , No Data (200)
    public static <T> Header<T> OK(ResultCode resultCode) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(resultCode.getCode())
                .description(resultCode.getMessage())
                .build();
    }

    // OK , Data (200)
    public static <T> Header<T> OK(T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(ResultCode.OK.getCode())
                .description(ResultCode.OK.getMessage())
                .data(data)
                .build();
    }

    // OK , Data (200)
    public static <T> Header<T> OK(ResultCode resultCode, T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(resultCode.getCode())
                .description(resultCode.getMessage())
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> Error(ResultCode resultCode) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(resultCode.getCode())
                .description(resultCode.getMessage())
                .build();
    }
}