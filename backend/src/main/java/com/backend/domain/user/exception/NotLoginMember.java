package com.backend.domain.user.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class NotLoginMember extends BusinessLogicException {

    public NotLoginMember() {
        super(ExceptionCode.NOT_LOGIN_MEMBER.getMessage(), ExceptionCode.NOT_LOGIN_MEMBER);
    }
}
