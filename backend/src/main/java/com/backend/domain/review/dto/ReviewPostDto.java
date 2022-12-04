package com.backend.domain.review.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class ReviewPostDto {

    @NotBlank
    private String reviewContent;

    @NotNull
    private int star;

}
