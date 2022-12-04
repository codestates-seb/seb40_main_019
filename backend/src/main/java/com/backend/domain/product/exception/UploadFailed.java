package com.backend.domain.product.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class UploadFailed extends BusinessLogicException {
    public UploadFailed() {
        super(ExceptionCode.Upload_Failed.getMessage(), ExceptionCode.Upload_Failed);
    }
}
