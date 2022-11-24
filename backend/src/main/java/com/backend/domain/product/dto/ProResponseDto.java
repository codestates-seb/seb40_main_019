package com.backend.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProResponseDto {
    @NotNull
    private int price;

    @NotBlank
    private String productName;

    @NotNull
    private String average;

    private String titleImg;

    private Long categoryId;

    private LocalDateTime createdAt;


}
