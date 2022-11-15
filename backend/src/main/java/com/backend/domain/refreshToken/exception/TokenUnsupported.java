package com.backend.domain.refreshToken.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class TokenUnsupported extends BusinessLogicException {

    public TokenUnsupported() {
        super(ExceptionCode.TOKEN_UNSUPPORTED.getMessage(), ExceptionCode.TOKEN_UNSUPPORTED);
    }
}
