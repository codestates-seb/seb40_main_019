package com.backend.domain.order.dto;

import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.domain.OrderProductReviewStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderProductDto {


    private Long productId;
    private int quantity;
    private int price;

    private int totalPrice;

    private String productName;

    private OrderProductReviewStatus reviewStatus;


    private String imgUrl;


    public OrderProductDto(OrderProduct orderProduct){
        this.productId = orderProduct.getProduct().getProductId();
        this.quantity = orderProduct.getQuantity();
        this.price = orderProduct.getPrice();
        this.totalPrice = orderProduct.getTotalPrice();
        this.productName = orderProduct.getProduct().getProductName();
        this.reviewStatus = orderProduct.getReviewStatus();
        this.imgUrl = orderProduct.getProduct().getTitleImg();
    }


}
