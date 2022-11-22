package com.backend.domain.product.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;



@Getter
public class TitleImg {

    private MultipartFile titleImg;

    public TitleImg(MultipartFile titleImg) {
        this.titleImg = titleImg;
    }
}
