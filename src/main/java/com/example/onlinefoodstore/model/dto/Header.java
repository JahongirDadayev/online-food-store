package com.example.onlinefoodstore.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> implements Serializable {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionTime;

    private String resultCode;

    private String resultMsg;

    private T data;

    private PaginationData pagination;

    public static <T> Header<T> ok() {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("0000")
                .resultMsg("OK")
                .build();
    }

    public static <T> Header<T> ok(T data) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("0000")
                .resultMsg("OK")
                .data(data)
                .build();
    }

    public static <T> Header<T> ok(T data, PaginationData pagination) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("0000")
                .resultMsg("OK")
                .data(data)
                .pagination(pagination)
                .build();
    }

    public static <T> Header<T> error() {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("E000")
                .resultMsg("ERROR")
                .build();
    }

    public static <T> Header<T> error(String msg) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode("E000")
                .resultMsg(msg)
                .build();
    }

    public static <T> Header<T> error(String customCode,
                                      String msg) {
        return Header.<T>builder()
                .transactionTime(LocalDateTime.now())
                .resultCode(customCode)
                .resultMsg(msg)
                .build();
    }
}