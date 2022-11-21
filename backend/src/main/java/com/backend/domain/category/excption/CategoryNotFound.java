package com.backend.domain.category.excption;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class CategoryNotFound extends BusinessLogicException {

    public CategoryNotFound(){super(ExceptionCode.CATEGORY_NOT_FOUND.getMessage(),ExceptionCode.CATEGORY_NOT_FOUND);}


}
