package com.backend.domain.product.application;

import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.exception.ProductExist;
import com.backend.domain.product.exception.ProductNotFound;
import com.backend.domain.review.dao.ReviewRepository;
import com.backend.domain.review.domain.Review;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    // 제품 생성
    @Transactional
    public Product create(Long userId,Product product){

        // 상품 이름 중복 검사
        existSameName(product.getProductName());
        // 유저 정보 검사
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);

        product.setUser(user);
        return productRepository.save(product);
    }



    // 상품 수정
    @Transactional
    public Product update(Long productsId, Product product) {
        Product findProduct = productRepository.findById(productsId).orElseThrow(ProductNotFound::new);

        Optional.ofNullable(product.getProductId())
                .ifPresent(findProduct::setProductId);
        Optional.ofNullable(product.getProductName())
                .ifPresent(findProduct::setProductName);
        Optional.ofNullable(product.getPrice())
                .ifPresent(findProduct::setPrice);

        return productRepository.save(findProduct);
    }

    @Transactional
    public Long delete(Long productsId) {
        Product product = productRepository.findById(productsId).orElseThrow(ProductNotFound::new);

        productRepository.delete(product);

        return  productsId;
    }

    @Transactional
    public Page<Product> getLists(int page,int size) {

        return productRepository.findAll(PageRequest.of(page, size, Sort.by("productId").descending()));
    }

    // 제품 이름이 같을 경우 오류 발생
    private void existSameName(String productName) {
        if (productRepository.existsByProductName(productName)){
            // 상품 이름이 겹치면 에러
            throw new ProductExist();
        }
    }

    public Product getList(Long productId) {
        return productRepository.findById(productId).orElseThrow(ProductNotFound::new);
    }

    public long calculateReviewAverage(Long productId){

        List<Review> reviews = reviewRepository.findByProductId(productId);
        if (reviews.isEmpty()){
            return 0;
        }else {
            int totalScore = reviews.stream()
                    .mapToInt(Review::getStar)
                    .sum();
            int totalUsers = reviews.size();

            return totalScore / totalUsers;
        }
    }
}