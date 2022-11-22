package com.backend.domain.product.application;

import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.review.dao.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
}
