package com.backend.domain.refreshToken.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class TokenSignatureInvalid extends BusinessLogicException {

    public TokenSignatureInvalid() {
        super(ExceptionCode.TOKEN_SIGNATURE_INVALID.getMessage(), ExceptionCode.TOKEN_SIGNATURE_INVALID);
    }
}
