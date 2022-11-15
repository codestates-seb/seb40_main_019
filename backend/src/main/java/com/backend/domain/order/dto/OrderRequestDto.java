package com.backend.domain.order.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;
@Getter @Setter
@AllArgsConstructor
@ToString
public class OrderRequestDto {

    private String receiverAddress;
    private String receiverName;
    private String receiverZipcode;
    private String receiverPhone;
    private List<ProductInfo> products;


    @Setter @Getter
    @AllArgsConstructor
    public static class ProductInfo {
        private Long productId;
        private String productName;
        private Integer quantity;
        private Integer price;

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