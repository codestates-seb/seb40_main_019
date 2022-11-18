package com.backend.domain.review.application;

import com.backend.domain.product.dao.ProductRepository;
import com.backend.domain.product.domain.Product;
import com.backend.domain.product.exception.ProductNotFound;
import com.backend.domain.review.dao.ReviewRepository;
import com.backend.domain.review.domain.Review;
import com.backend.domain.review.exception.ReviewNotFound;
import com.backend.domain.user.dao.UserRepository;
import com.backend.domain.user.domain.User;
import com.backend.domain.user.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Review create(Long userId, Review review,Long productId) {
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFound::new);

        review.setReviewWriter(user.getUsername());
        review.setUser(user);
        review.setProduct(product);
        return reviewRepository.save(review);
    }
    @Transactional
    public Review update(Long reviewId, Review review) {
        Review findReview = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);

        Optional.ofNullable(review.getReviewId())
                .ifPresent(findReview::setReviewId);
        Optional.ofNullable(review.getReviewContent())
                .ifPresent(findReview::setReviewContent);
        Optional.ofNullable(review.getStar())
                .ifPresent(findReview::setStar);
        return reviewRepository.save(findReview);
    }

    @Transactional
    public Long delete(Long reviewId){
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        reviewRepository.delete(review);
        return reviewId;
    }


    public Page<Review> getList(Long userId,int page,int size) {
        return reviewRepository.findByUser(userId, PageRequest.of(page, size, Sort.by("reviewId").descending()));
    }

    public Page<Review> getListProduct(Long productId, int page, int size) {
        return reviewRepository.findByProduct(productId,PageRequest.of(page,size,Sort.by("reviewId").descending()));
    }
}
