package com.bootproj.pmcweb.Network;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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
                .resultCode(ResultCode.RESULT_OK)
                .description("OK")
                .build();
    }

    // OK , Data (200)
    public static <T> Header<T> OK(T data) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(ResultCode.RESULT_OK)
                .description("OK")
                .data(data)
                .build();
    }

    // ERROR
    public static <T> Header<T> Error(String description) {
        return (Header<T>)Header.builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(ResultCode.RESULT_ERROR)
                .description(description)
                .build();
    }
}