package com.gom.login.constans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0, "Success"),
    DUPLICATED_USER_ID(10000, ""),
    BAD_REQUEST(20000, "Bad Request"),
    INTERNAL_ERROR(20001, "Server Internal Error"),
    SPRING_INTERNAL_ERROR(20002, "Spring Internal Error");

    private final Integer code;
    private final String message;

}
