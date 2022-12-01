package com.backend.domain.product.application;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    @SneakyThrows
    String StoreImage(MultipartFile img);

}
