package com.backend.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {

    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

}