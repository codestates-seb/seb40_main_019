package com.backend.domain.product.dto;

import com.backend.domain.product.domain.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProResponseDto {
    @NotNull
    private int price;

    @NotBlank
    private String productName;

    @NotNull
    private String average;
}
