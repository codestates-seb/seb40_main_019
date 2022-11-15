package com.backend.domain.order.dto;

import com.backend.domain.order.domain.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderPatchDto {
    private long orderId;

    private OrderStatus orderStatus;


    private String receiverName;

    private String receiverPhone;

    private String receiverAddress;

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /* @Builder
    private OrderPatchDto(long orderId, OrderStatus orderStatus, String receiverName, String receiverPhone, String receiverAddress){
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverAddress = receiverAddress;
    }*/
}
//1차완
//orderId를 받아야하는게 맞나?