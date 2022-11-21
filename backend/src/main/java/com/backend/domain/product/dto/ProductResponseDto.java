package com.backend.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductResponseDto {

    private int productId;
    @NotNull
    private int price;

    @NotBlank
    private String productName;


}
