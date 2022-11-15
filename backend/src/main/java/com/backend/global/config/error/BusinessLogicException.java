package com.backend.global.config.error;

import lombok.Getter;

public class BusinessLogicException extends RuntimeException {
    @Getter
    private ExceptionCode  exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode =exceptionCode;
    }

    public BusinessLogicException(String message, ExceptionCode exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
