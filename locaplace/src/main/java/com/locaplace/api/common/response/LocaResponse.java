package com.locaplace.api.common.response;

import com.locaplace.api.common.enums.ErrorCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocaResponse<T>{
    private int status = 200;
    private T data;
    private String code;
    private String message;

    public static <T> LocaResponse<T> ok(T data, String message) {
        return new LocaResponse<>(200, data, "OK", message);
    }

    public static <T> LocaResponse<T> of(int status, T data, String code, String message) {
        return new LocaResponse<>(status, data, code, message);
    }

    public static <T> LocaResponse<T> error(ErrorCode errorCode) {
        return new LocaResponse<>(errorCode.getStatusCode(), null, errorCode.getCode(), errorCode.getMessage());
    }

}
