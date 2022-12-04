package com.backend.domain.order.exception;

import com.backend.global.error.BusinessLogicException;
import com.backend.global.error.ExceptionCode;

public class OrderNotFound extends BusinessLogicException {
    public OrderNotFound() { super(ExceptionCode.ORDER_NOT_FOUND.getMessage(), ExceptionCode.ORDER_NOT_FOUND);}
}
