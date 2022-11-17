package com.backend.domain.refreshToken.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class TokenExpired extends BusinessLogicException {

    public TokenExpired() {
        super(ExceptionCode.TOKEN_EXPIRED.getMessage(),ExceptionCode.TOKEN_EXPIRED);
    }
}
