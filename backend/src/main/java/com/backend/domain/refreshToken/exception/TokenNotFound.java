package com.backend.domain.refreshToken.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class TokenNotFound extends BusinessLogicException {

    public TokenNotFound() {
        super(ExceptionCode.TOKEN_NOT_FOUND.getMessage(), ExceptionCode.TOKEN_NOT_FOUND);
    }
}
