package com.backend.domain.order.dto;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistoryDto {

    public OrderHistoryDto(Order order){
        this.orderId = order.getOrderId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
    }

    private Long orderId; //주문아이디

    private String orderDate; //주문 날짜

    private OrderStatus orderStatus; //주문 상태

    private List<OrderProductDto> orderProductDtoList = new ArrayList<>(); //주문상품리스트

    public void addOrderProductDto(OrderProductDto orderProductDto){
        orderProductDtoList.add(orderProductDto);
    }

}
