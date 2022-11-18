package com.backend.domain.review.domain;
import com.backend.domain.product.domain.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Column(nullable = false)
    private String reviewWriter;

    @Column(nullable = false)
    private String reviewContent;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "review_star_id")
    private ReviewStar reviewStar;

}
