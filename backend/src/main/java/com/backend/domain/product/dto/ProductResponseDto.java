package com.backend.domain.product.dto;

import com.backend.domain.product.domain.Product;
import com.backend.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductResponseDto {

    @NotNull
    private int price;

    @NotBlank
    private String productName;

    @NotBlank
    private String seller;

    @NotNull
    private int stock;

    private int star;

    @Builder
    public ProductResponseDto(int price, String productName, String seller, int stock, int star) {
        this.price = price;
        this.productName = productName;
        this.seller = seller;
        this.stock = stock;
        this.star = star;
    }

    // 포스트를 리스폰스로 변형


}
