package com.backend.domain.order.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter

public class OrderDto {

    private String receiverAddress;
    private String receiverName;
    private String receiverZipcode;
    private String receiverPhone;


    @NotNull(message = "상품 아이디는 필수 입력 값입니다.")
    private Long productId;

    @Min(value = 1, message = "최소 주문 수량은 1개 입니다.")
    @Max(value = 999, message = "최대 주문 수량은 999개 입니다.")
    private int quantity;

    public static OrderDto createOrderDto(Long productId, int quantity){
        OrderDto orderDto = new OrderDto();
        orderDto.setProductId(productId);
        orderDto.setQuantity(quantity);
        return orderDto;
    }

    //2차 완
}




   /* @Builder
    private OrderPostDto(long userId, String receiverName, String receiverPhone, String receiverAddress, List<OrderProductDto> orderProducts, Long productId) {
        this.userId = userId;
        this.orderProducts = orderProducts;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.receiverName = receiverName;
        this.productId = productId;
    }*/

// 1차완