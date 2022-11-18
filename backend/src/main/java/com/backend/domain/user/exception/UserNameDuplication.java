package com.backend.domain.user.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class UserNameDuplication extends BusinessLogicException {

    public UserNameDuplication() {
        super(ExceptionCode.USERNAME_DUPLICATION.getMessage(), ExceptionCode.USERNAME_DUPLICATION);
    }
}
