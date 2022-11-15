package com.backend.domain.product.application;

import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.dto.ProductPatchDto;
import com.backend.domain.product.dto.ProductPostDto;
import com.backend.domain.product.exception.ProductExist;
import com.backend.domain.product.exception.ProductNotFound;
import com.backend.global.config.error.BusinessLogicException;
import com.backend.global.config.error.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 데이터 조회만 가능
public class ProductService {

    private final ProductRepository productRepository;

    // 제품 생성
    @Transactional
    public Long create(ProductPostDto productPostDto){

        existSameName(productPostDto.getProductName());
        Product product = Product.createProduct(productPostDto);
        return productRepository.save(product).getProductId();
    }

    // 제품 이름이 같을 경우 오류 발생
    private void existSameName(String productName) {
        if (productRepository.existsByProductName(productName)){
            // 상품 이름이 겹치면 에러
            throw new ProductExist();
        }
    }

    // 상품 수정
    @Transactional
    public Long update(Long productsId, ProductPatchDto productPatchDto) {
        Product product = productRepository.findById(productsId).orElseThrow(ProductNotFound::new);

        product.updateProduct(productPatchDto);

        return productRepository.save(product).getProductId();
    }

    @Transactional
    public Long delete(Long productsId) {
        Product product = productRepository.findById(productsId).orElseThrow(ProductNotFound::new);

        productRepository.delete(product);

        return  productsId;
    }
    // 조회 할떄 값이 변경될까바

}