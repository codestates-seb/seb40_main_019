package com.backend.domain.product.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class DetailImg {
    private MultipartFile detailImg;

    public DetailImg(MultipartFile detailImg) {
        this.detailImg = detailImg;
    }
}
