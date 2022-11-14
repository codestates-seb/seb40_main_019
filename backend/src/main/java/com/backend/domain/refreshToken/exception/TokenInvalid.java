package com.backend.domain.refreshToken.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class TokenInvalid extends BusinessLogicException {

    public TokenInvalid() {
        super(ExceptionCode.TOKEN_INVALID.getMessage(), ExceptionCode.TOKEN_INVALID);
    }
}
