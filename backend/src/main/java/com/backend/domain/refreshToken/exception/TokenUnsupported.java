package com.backend.domain.refreshToken.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class TokenUnsupported extends BusinessLogicException {

    public TokenUnsupported() {
        super(ExceptionCode.TOKEN_UNSUPPORTED.getMessage(), ExceptionCode.TOKEN_UNSUPPORTED);
    }
}
