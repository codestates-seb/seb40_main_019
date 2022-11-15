package com.backend.domain.product.domain;

import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.product.dto.ProductPatchDto;
import com.backend.domain.product.dto.ProductPostDto;
import com.backend.domain.review.domain.Review;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(nullable = false)
    private String seller;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private int star;

    @Column(nullable = false)
    private int discountPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    // 카테고리 맵핑 수정
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProductCategory> productCategories = new ArrayList<>();


//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "product_id")
//    private List<Cart> carts = new ArrayList<>();

    @Builder
    public Product(Long productId, int price, String productName, int stock, int star, int discountPrice, List<Review> reviews,
                   List<OrderProduct> orderProducts,String seller ) {
        this.productId = productId;
        this.price = price;
        this.productName = productName;
        this.stock = stock;
        this.star = star;
        this.discountPrice = discountPrice;
        this.reviews = reviews;
        this.orderProducts = orderProducts;
        this.seller = seller;
    }

    // 제품 신규 등록
    public static Product createProduct(ProductPostDto productPostDto){
        Product product = Product.builder()
                .productName(productPostDto.getProductName())
                .seller(productPostDto.getSeller())
                .price(productPostDto.getPrice())
                .stock(productPostDto.getStock())
                .build();
        return product;
    }

    //  제품 변경
    public void updateProduct(ProductPatchDto productPatchDto){
        String changedProductName = productPatchDto.getProductName();
        String changedSeller = productPatchDto.getSeller();
        Integer changedStock = productPatchDto.getStock();
        Integer changedPrice = productPatchDto.getPrice();

        this.productName = changedProductName == null ? this.productName : changedProductName;
        this.seller = changedSeller == null ? this.seller : changedSeller;
        this.price = changedPrice == null ? this.price : changedPrice;
        this.stock = changedStock == null ? this.stock : changedStock;

    }

}