package com.locaplace.api.common.response;

import com.locaplace.api.common.enums.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LocaException extends RuntimeException {
    private final ErrorCode errorCode;
}
