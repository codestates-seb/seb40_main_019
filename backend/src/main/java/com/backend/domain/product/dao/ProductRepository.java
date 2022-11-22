package com.backend.domain.product.dao;

import com.backend.domain.product.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByProductName(String productName);

    @Query("select p from Product p where p.category.categoryId = :categoryId")
    Page<Product> findByCategory(@Param("categoryId")long categoryId , Pageable pageable);
}