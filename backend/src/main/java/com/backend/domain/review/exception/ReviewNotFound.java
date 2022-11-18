package com.backend.domain.review.exception;


import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class ReviewNotFound extends BusinessLogicException {

    public ReviewNotFound(){super(ExceptionCode.REVIEW_NOT_FOUND.getMessage(),ExceptionCode.REVIEW_NOT_FOUND);}
}
