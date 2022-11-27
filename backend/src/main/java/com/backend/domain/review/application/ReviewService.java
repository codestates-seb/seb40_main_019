package com.backend.domain.review.application;

import com.backend.domain.order.dao.OrderProductRepository;
import com.backend.domain.order.dao.OrderRepository;
import com.backend.domain.order.domain.Order;
import com.backend.domain.order.domain.OrderProduct;
import com.backend.domain.order.domain.OrderProductReviewStatus;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    @Transactional
    public Review create(Long userId,Long productId,String reviewContent,int star,String reviewUrl) {
        log.info("create 실행 ");
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        log.info("user : ",user);
        Product product = productRepository.findById(productId).orElseThrow(ProductNotFound::new);
        log.info("product : ",product);

        Review review = Review.builder()
                .reviewImg(reviewUrl)
                .reviewWriter(user.getNickname())
                .reviewContent(reviewContent)
                .star(star)
                .user(user)
                .product(product)
                .build();
        log.info("review : ",review);
        OrderProduct orderProduct = orderProductRepository.findOrderProduct(userId, productId);
        orderProduct.setReviewStatus(OrderProductReviewStatus.WRITED);
        return reviewRepository.save(review);
    }
    @Transactional
    public Review update(Long reviewId,Long userId,String reviewContent,int star,String reviewUrl) {
        log.info("upate 실행");
        Review findReview = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        log.info("findReview : ",findReview);
        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        log.info("user : ",user);
        Optional.ofNullable(reviewId)
                .ifPresent(findReview::setReviewId);
        Optional.ofNullable(reviewContent)
                .ifPresent(findReview::setReviewContent);
        Optional.ofNullable(star)
                .ifPresent(findReview::setStar);
        Optional.ofNullable(reviewUrl)
                .ifPresent(findReview::setReviewImg);
        log.info("findReview : ",findReview);
        return reviewRepository.save(findReview);
    }

    @Transactional
    public Long delete(Long reviewId){
        log.info("delete 실행");
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        log.info("review : ", review);
        reviewRepository.delete(review);
        return reviewId;
    }

    @Transactional
    public Page<Review> getList(Long userId,int page,int size) {
        log.info("getList 실행");
        return reviewRepository.findByUser(userId, PageRequest.of(page, size, Sort.by("reviewId").descending()));
    }

    @Transactional
    public Page<Review> getListProduct(Long productId, int page, int size) {
        log.info("getListProduct 실행");
        return reviewRepository.findByProduct(productId,PageRequest.of(page,size,Sort.by("reviewId").descending()));
    }

    @Transactional
    public Review getRead(Long reviewId) {
        log.info("getRead 실행");
        Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFound::new);
        log.info("review : ", review);
        return review;
    }
}
