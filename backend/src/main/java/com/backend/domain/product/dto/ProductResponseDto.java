package com.backend.domain.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDto {

    private int productId;
    @NotNull
    private int price;

    @NotBlank
    private String productName;

    private String titleImg;

    private String detailImg;

    private Long categoryId;

}
