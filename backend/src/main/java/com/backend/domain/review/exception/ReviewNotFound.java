package com.backend.domain.review.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class ReviewNotFound extends BusinessLogicException {

    public ReviewNotFound(){super(ExceptionCode.REVIEW_NOT_FOUND.getMessage(),ExceptionCode.REVIEW_NOT_FOUND);}
}
