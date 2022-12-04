package com.backend.domain.product.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class NoImage extends BusinessLogicException {
    public NoImage() {
        super(ExceptionCode.No_Image.getMessage(),ExceptionCode.No_Image);
    }
}
