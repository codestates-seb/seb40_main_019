package com.backend.domain.refreshToken.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class TokenInvalid extends BusinessLogicException {

    public TokenInvalid() {
        super(ExceptionCode.TOKEN_INVALID.getMessage(), ExceptionCode.TOKEN_INVALID);
    }
}
