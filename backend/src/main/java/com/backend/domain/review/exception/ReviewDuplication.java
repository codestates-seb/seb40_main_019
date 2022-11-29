package com.backend.domain.review.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class ReviewDuplication extends BusinessLogicException {
    public ReviewDuplication() {
        super(ExceptionCode.REVIEW_DUPLICATION.getMessage(),ExceptionCode.REVIEW_DUPLICATION);
    }
}
