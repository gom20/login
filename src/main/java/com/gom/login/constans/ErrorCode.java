package com.gom.login.constans;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    OK(0, "Success"),
    DUPLICATED_USER_ID(10000, "Duplicated User Id Error"),
    BAD_REQUEST(20000, "Bad Request"),
    INTERNAL_ERROR(20001, "Server Internal Error"),
    SPRING_INTERNAL_ERROR(20002, "Spring Internal Error");

    private final Integer code;
    private final String message;

    public String getMessage(String message){
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(getMessage());
    }

}
