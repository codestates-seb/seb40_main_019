package com.backend.domain.order.dto;
import com.querydsl.core.annotations.QueryProjection;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrderProductDto {
    private Long orderProductId;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Integer price;

    @QueryProjection
    public OrderProductDto(Long orderProductId, Long productId,String productName, Integer quantity, Integer price) {
        this.orderProductId = orderProductId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}


   /* @Builder
    private OrderProductDto(long productId, int orderProductQuantity, int price) {
        this.productId = productId;
        this.orderProductQuantity = orderProductQuantity;
        this.price = price;
    }*/



