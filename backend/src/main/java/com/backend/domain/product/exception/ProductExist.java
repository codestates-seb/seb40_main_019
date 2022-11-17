package com.backend.domain.product.exception;


import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class ProductExist extends BusinessLogicException {
    public ProductExist(){
        super(ExceptionCode.PRODUCT_EXIST.getMessage(),ExceptionCode.PRODUCT_EXIST);
    }
}