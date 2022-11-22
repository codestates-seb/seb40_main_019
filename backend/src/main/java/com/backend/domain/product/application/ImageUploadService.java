package com.backend.domain.product.application;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    String StoreImage(MultipartFile img);

}
