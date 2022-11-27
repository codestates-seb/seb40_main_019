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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
                .productId(product.getProductId())
                .productName(product.getProductName())
                .titleImg(product.getTitleImg())
                .build();
        log.info("review : ",review);
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

    @Transactional(readOnly = true)
    public Page<Review> getProductReview(Long userId,int page,int size) {
        List<Review> response = new ArrayList<>();
        PageRequest pageable = PageRequest.of(page, size, Sort.by("reviewId").descending());

        User user = userRepository.findById(userId).orElseThrow(MemberNotFound::new);
        List<Product> product = productRepository.findByUserId(userId);

        for (Product list : product) {
            List<Review> byProductId = reviewRepository.findByProductId(list.getProductId());
            response.addAll(byProductId);
        }
//        Collections.sort(response, new Comparator<Review>() {
//            @Override
//            public int compare(Review o1, Review o2) {
//                if(o1.getReviewId() < o2.getReviewId()) {
//                    return 1;
//                }else if (o1.getReviewId() == o2.getReviewId()){
//                    return o1.getProductName().compareTo(o2.getProductName());
//                }else {
//                    return -1;
//                }
//
//            }
//        });
        int start =(int) pageable.getOffset();
        int end = Math.min((start+ pageable.getPageSize()), response.size());
        Page<Review> reviewPage = new PageImpl<>(response.subList(start,end),pageable, response.size());
        
        return reviewPage;
    }
}
