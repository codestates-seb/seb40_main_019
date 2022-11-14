package com.backend.domain.refreshToken.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class TokenEmpty extends BusinessLogicException {

    public TokenEmpty() {
        super(ExceptionCode.TOKEN_ILLEGAL_ARGUMENT.getMessage(), ExceptionCode.TOKEN_ILLEGAL_ARGUMENT);
    }
}
