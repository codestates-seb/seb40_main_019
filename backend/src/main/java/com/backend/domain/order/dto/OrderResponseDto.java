package com.backend.domain.order.dto;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.domain.OrderStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
    private OrderStatus ordersStatus;
    private int totalPrice;
    private List<OrderProductDto> orderProductList;
    private Long userId;

}
