package com.backend.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPatchDto {



    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;



}
//1차완
//orderId를 받아야하는게 맞나?
