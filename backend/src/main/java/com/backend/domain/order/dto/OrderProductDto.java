package com.backend.domain.order.dto;
import com.backend.domain.order.domain.OrderProduct;
import com.querydsl.core.annotations.QueryProjection;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter @Setter
public class OrderProductDto {

    public OrderProductDto(OrderProduct orderProduct){
        this.productName = orderProduct.getProduct().getProductName();
        this.quantity = orderProduct.getQuantity();
        this.price = orderProduct.getPrice();
        //this.imgUrl = imgUrl;
    }
    private String productName;
    private int quantity;
    private int price;

    //private String imgUrl;


}




