package com.gom.login.error;

import com.gom.login.constans.ErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException{
    private final ErrorCode errorCode;
    private String detailMessage;

    public GeneralException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = errorCode.getMessage();
    }

    public GeneralException(ErrorCode errorCode, String detailMessage){
        super(detailMessage);
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;
    }

}
