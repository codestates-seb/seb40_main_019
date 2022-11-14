package com.backend.domain.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderProductDto {
    private long productId;

    private int orderProductQuantity;

    @Builder
    private OrderProductDto(long productId, int orderProductQuantity) {
        this.productId = productId;
        this.orderProductQuantity = orderProductQuantity;
    }

}

