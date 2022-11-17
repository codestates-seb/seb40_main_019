package com.backend.domain.product.exception;

import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;

public class ProductNotFound extends BusinessLogicException {

    public ProductNotFound(){
        super(ExceptionCode.PRODUCT_NOT_FOUND.getMessage(),ExceptionCode.PRODUCT_NOT_FOUND);
    }
}