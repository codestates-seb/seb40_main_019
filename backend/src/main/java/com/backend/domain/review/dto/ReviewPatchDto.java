package com.backend.domain.review.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class ReviewPatchDto {
    @NotBlank
    private String reviewContent;

    @NotNull
    private int star;

}
