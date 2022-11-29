package com.backend.domain.product.domain;

import com.backend.domain.category.domain.Category;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.review.domain.Review;
import com.backend.domain.user.domain.User;
import com.backend.global.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Product extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int discountPrice;

    private String titleImg;

    private String detailImg;

    private String tag;

    @ColumnDefault("0")
    private int viewCount;

    // 유저 맵핑 추가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    // 카테고리 맵핑 수정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    public void setTag(String tag) {
        this.tag = tag;
    }
    public void ViewCountPlus(int viewCount){
        this.viewCount +=1;
    }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public void setDetailImg(String detailImg) {
        this.detailImg = detailImg;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
