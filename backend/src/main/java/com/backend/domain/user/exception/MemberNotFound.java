package com.backend.domain.user.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class MemberNotFound extends BusinessLogicException {

    public MemberNotFound() {
        super(ExceptionCode.USER_NOT_FOUND.getMessage(), ExceptionCode.USER_NOT_FOUND);
    }
}
