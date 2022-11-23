package com.backend.domain.order.dto;

import com.backend.domain.order.domain.OrderStatus;
import lombok.*;

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