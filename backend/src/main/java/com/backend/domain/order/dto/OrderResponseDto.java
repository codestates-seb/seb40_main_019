package com.backend.domain.order.dto;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderResponseDto {
    private Long orderId;
    private Long userId;      //aggregate
    private List<OrderProductDto> orderProducts ;
    private String receiverAddress;
    private String receiverName;
    private String receiverPostcode;
    private String receiverPhone;
    private OrderStatus orderStatus;
    private int totalPrice;

}


   /* @Builder
    public OrderResponseDto(Long orderId,LocalDateTime createdAt, OrderStatus orderStatus,int totalPrice, List<OrderProductDto> orderProductList) {
    this. orderId = orderId;
    this.createdAt = createdAt;
    this.totalPrice = totalPrice;
    this.orderProductList = orderProductList;
    }*/




    /*public static OrderResponseDto toResponse(Order order) {
        return OrderResponseDto.builder()
                .orderId(order.getOrderId())
                .createdAt(order.getCreatedAt())
                .totalPrice(order.getTotalPrice())
                .orderProductList(order.getOrderProducts())
                .build();
}*/
