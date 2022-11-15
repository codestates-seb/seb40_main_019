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
    private Integer productQuantity;
    private Integer productPrice;

    @QueryProjection
    public OrderProductDto(Long orderProductId, Long productId,String productName, Integer productQuantity, Integer productPrice, String productSize, String productColor) {
        this.orderProductId = orderProductId;
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }
}


   /* @Builder
    private OrderProductDto(long productId, int orderProductQuantity, int price) {
        this.productId = productId;
        this.orderProductQuantity = orderProductQuantity;
        this.price = price;
    }*/



