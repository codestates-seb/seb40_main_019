package com.backend.domain.product.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ProductPostDto {

    @NotNull
    private int price;

    @NotBlank
    private String productName;



}
