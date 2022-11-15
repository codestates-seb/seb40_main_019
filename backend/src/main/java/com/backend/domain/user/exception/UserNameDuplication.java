package com.backend.domain.user.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class UserNameDuplication extends BusinessLogicException {

    public UserNameDuplication() {
        super(ExceptionCode.USERNAME_DUPLICATION.getMessage(), ExceptionCode.USERNAME_DUPLICATION);
    }
}
