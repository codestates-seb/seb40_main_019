package com.backend.domain.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryId;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private String categoryRefCode;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
