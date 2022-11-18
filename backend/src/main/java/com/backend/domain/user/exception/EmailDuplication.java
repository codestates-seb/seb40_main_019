package com.backend.domain.user.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class EmailDuplication extends BusinessLogicException {

    public EmailDuplication() {
        super(ExceptionCode.EMAIL_DUPLICATION.getMessage(), ExceptionCode.EMAIL_DUPLICATION);
    }
}
