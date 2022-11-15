package com.backend.domain.product.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductPatchDto {

    @NotNull
    private int price;

    @NotBlank
    private String productName;

    @NotBlank
    private String seller;

    @NotNull
    private int stock;

    @Builder
    private ProductPatchDto(int price, String productName, String seller, int stock) {
        this.price = price;
        this.productName = productName;
        this.seller = seller;
        this.stock = stock;
    }
}