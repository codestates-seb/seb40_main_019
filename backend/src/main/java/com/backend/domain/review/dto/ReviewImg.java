package com.backend.domain.review.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class ReviewImg {
    private MultipartFile reviewImg;

    public ReviewImg(MultipartFile reviewImg) {
        this.reviewImg = reviewImg;
    }
}
