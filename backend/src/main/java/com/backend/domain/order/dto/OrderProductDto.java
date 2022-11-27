package com.backend.domain.order.dto;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.domain.OrderProductReviewStatus;
import com.querydsl.core.annotations.QueryProjection;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter @Setter
public class OrderProductDto {

    public OrderProductDto(OrderProduct orderProduct){
        this.productId = orderProduct.getProduct().getProductId();
        this.quantity = orderProduct.getQuantity();
        this.price = orderProduct.getPrice();
        this.totalPrice = orderProduct.getTotalPrice();
        this.productName = orderProduct.getProduct().getProductName();
        this.reviewStatus = orderProduct.getReviewStatus();
        this.imgUrl = orderProduct.getProduct().getTitleImg();
    }
    private Long productId;
    private int quantity;
    private int price;

    private int totalPrice;

    private String productName;

    private OrderProductReviewStatus reviewStatus;


    private String imgUrl;


}




