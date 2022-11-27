package com.backend.domain.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CartOrderDto {

    private String receiverAddress;
    private String receiverName;
    private String receiverZipcode;
    private String receiverPhone;

    private List<CartOrderProductDto> cartOrderProductDtoList;

}