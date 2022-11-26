package com.backend.domain.payment.mapper;


import com.backend.domain.payment.domain.Payment;
import com.backend.domain.payment.dto.PaymentRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment paymentRequestToPayment(PaymentRequest paymentRequest);
}
