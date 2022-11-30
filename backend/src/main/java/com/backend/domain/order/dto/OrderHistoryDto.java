package com.backend.domain.order.dto;

import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderHistoryDto {



    private Long orderId; //주문아이디

    private String orderDate; //주문 날짜

    private OrderStatus orderStatus; //주문 상태

    private List<OrderProductDto> orderProductDtoList = new ArrayList<>();//주문상품리스트

    private String receiverAddress;
    private String receiverName;
    private String receiverZipcode;
    private String receiverPhone;




    public void addOrderProductDto(OrderProductDto orderProductDto) {
        orderProductDtoList.add(orderProductDto);
    }

    public OrderHistoryDto(Order order) {
        this.orderId = order.getOrderId();
        this.orderDate = order.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
        this.receiverAddress = order.getReceiverAddress();
        this.receiverName = order.getReceiverName();
        this.receiverZipcode = order.getZipCode();
        this.receiverPhone = order.getReceiverPhone();

    }


    public static List<OrderHistoryDto> from(List<Order> orders) {
        List<OrderHistoryDto> orderHistoryDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderHistoryDto orderHistoryDto = new OrderHistoryDto(order);
            List<OrderProduct> orderProducts = order.getOrderProducts();
            for (OrderProduct orderProduct : orderProducts) {
                OrderProductDto orderProductDto = new OrderProductDto(orderProduct);
                orderHistoryDto.addOrderProductDto(orderProductDto);
            }
            orderHistoryDtos.add(orderHistoryDto);
        }
        return orderHistoryDtos;
    }
}
