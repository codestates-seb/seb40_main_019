package com.backend.domain.order.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class OrderProductDto {
    private long productId;

    private int orderProductQuantity;

    private int price;


   /* @Builder
    private OrderProductDto(long productId, int orderProductQuantity, int price) {
        this.productId = productId;
        this.orderProductQuantity = orderProductQuantity;
        this.price = price;
    }*/

}

