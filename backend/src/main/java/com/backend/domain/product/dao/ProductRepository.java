package com.backend.domain.product.dao;

import com.backend.domain.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByProductName(String productName);
}