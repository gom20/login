package com.gom.login.error;


import com.gom.login.dto.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.gom.login.constans.ErrorCode.INTERNAL_ERROR;
import static com.gom.login.constans.ErrorCode.SPRING_INTERNAL_ERROR;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class, // httpMethod 잘못된 요청으로 인한 Exception
            MethodArgumentNotValidException.class // @Validation Annotation 에서 걸러진 Exception
    })
    public ErrorResponse handleBadRequest(Exception e, HttpServletRequest request){
        return ErrorResponse.builder()
                .code(SPRING_INTERNAL_ERROR.getCode())
                .message(SPRING_INTERNAL_ERROR.getMessage())
                .build();
    }

    @ExceptionHandler(GeneralException.class)
    public ErrorResponse handleException(GeneralException e, HttpServletRequest request){
        e.printStackTrace();
        return ErrorResponse.builder()
                .code(e.getErrorCode().getCode())
                .message(e.getErrorCode().getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception e, HttpServletRequest request){
        e.printStackTrace();
        return ErrorResponse.builder()
                .code(INTERNAL_ERROR.getCode())
                .message(INTERNAL_ERROR.getMessage())
                .build();
    }
}
