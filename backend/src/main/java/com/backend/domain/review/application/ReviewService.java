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
    public Review create(Long userId,Long productId,String reviewContent,int star,String reviewUrl) {
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFound::new);

        Review review = Review.builder()
                .reviewImg(reviewUrl)
                .reviewWriter(user.getNickname())
                .reviewContent(reviewContent)
                .star(star)
                .user(user)
                .product(product)
                .build();

        return reviewRepository.save(review);
    }
    @Transactional
    public Review update(Long reviewId,Long userId,String reviewContent,int star,String reviewUrl) {
        Review findReview = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        Optional.ofNullable(reviewId)
                .ifPresent(findReview::setReviewId);
        Optional.ofNullable(reviewContent)
                .ifPresent(findReview::setReviewContent);
        Optional.ofNullable(star)
                .ifPresent(findReview::setStar);
        Optional.ofNullable(reviewUrl)
                .ifPresent(findReview::setReviewImg);

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

    public Review getRead(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        return review;
    }
}
