package com.backend.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReviewResponseDto {
    @NotBlank
    private String reviewWriter;
    @NotBlank
    private String reviewContent;
    @NotNull
    private int star;


}
