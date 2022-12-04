package com.backend.domain.order.dto;

import com.backend.domain.order.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    private OrderStatus orderStatus;
}