package com.backend.domain.order.dto;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
    private OrderStatus ordersStatus;
    private int totalPrice;
    private List<OrderProductDto> orderProductList;



   /* @Builder
    public OrderResponseDto(Long orderId,LocalDateTime createdAt, OrderStatus orderStatus,int totalPrice, List<OrderProductDto> orderProductList) {
    this. orderId = orderId;
    this.createdAt = createdAt;
    this.totalPrice = totalPrice;
    this.orderProductList = orderProductList;
    }*/
}



    /*public static OrderResponseDto toResponse(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .createdAt(order.getCreatedAt())
                .totalPrice(order.getTotalPrice())
                .orderProductList(order.getOrderProducts())
                .build();
}*/
