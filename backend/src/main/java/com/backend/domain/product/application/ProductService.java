package com.backend.domain.product.application;

import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.dto.ProductPatchDto;
import com.backend.domain.product.dto.ProductPostDto;
import com.backend.domain.product.dto.ProductResponseDto;
import com.backend.domain.product.exception.ProductExist;
import com.backend.domain.product.exception.ProductNotFound;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.request.PageInfo;
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
@Transactional(readOnly = true) // 데이터 조회만 가능
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

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
        Optional.ofNullable(product.getSeller())
                .ifPresent(findProduct::setSeller);
        Optional.ofNullable(product.getStock())
                .ifPresent(findProduct::setStock);

        return productRepository.save(findProduct);
    }

    @Transactional
    public Long delete(Long productsId) {
        Product product = productRepository.findById(productsId).orElseThrow(ProductNotFound::new);

        productRepository.delete(product);

        return  productsId;
    }

    public Page<Product> getList(int page,int size) {

        return productRepository.findAll(PageRequest.of(page, size, Sort.by("productId").descending()));
    }

    // 제품 이름이 같을 경우 오류 발생
    private void existSameName(String productName) {
        if (productRepository.existsByProductName(productName)){
            // 상품 이름이 겹치면 에러
            throw new ProductExist();
        }
    }

}