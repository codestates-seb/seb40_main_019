package com.backend.domain.order.dto;

import com.backend.domain.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPriceDto {
    private int orderTotalPrice;
    private long orderId;

    public OrderPriceDto(Order order) {
        this.orderTotalPrice = order.getOrderTotalPrice();
        this.orderId = order.getOrderId();
    }
}
