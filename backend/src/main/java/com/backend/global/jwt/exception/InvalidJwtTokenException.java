package com.backend.global.jwt.exception;

import com.backend.global.error.ExceptionCode;
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
