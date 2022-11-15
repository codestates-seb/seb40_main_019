package com.backend.domain.order.dto;

import com.backend.domain.order.domain.OrderStatus;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderPatchDto {
    private long orderId;

    private OrderStatus orderStatus;


    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }


}
//1차완
//orderId를 받아야하는게 맞나?