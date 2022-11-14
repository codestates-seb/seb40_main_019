package com.backend.global.config.security.exception;

import com.backend.global.config.error.ExceptionCode;
import io.jsonwebtoken.JwtException;
import lombok.Getter;

public class InvalidJwtTokenException extends JwtException {
    @Getter
    private final ExceptionCode errorCode;

    public InvalidJwtTokenException(ExceptionCode errorCode) {
        super(null);
        this.errorCode = errorCode;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
