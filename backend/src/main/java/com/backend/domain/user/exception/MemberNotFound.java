package com.backend.domain.user.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class MemberNotFound extends BusinessLogicException {

    public MemberNotFound() {
        super(ExceptionCode.USER_NOT_FOUND.getMessage(), ExceptionCode.USER_NOT_FOUND);
    }
}
