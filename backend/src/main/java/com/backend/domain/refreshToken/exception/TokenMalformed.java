package com.backend.domain.refreshToken.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class TokenMalformed extends BusinessLogicException {

    public TokenMalformed() {
        super(ExceptionCode.TOKEN_MALFORMED.getMessage(), ExceptionCode.TOKEN_MALFORMED);
    }
}
