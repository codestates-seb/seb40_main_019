package com.backend.domain.review.api;

import com.backend.domain.review.Mapper.ReviewMapper;
import com.backend.domain.review.application.ReviewService;
import com.backend.domain.review.domain.Review;
import com.backend.domain.review.dto.ReviewImg;
import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.Response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;



    @PostMapping("/review/{productId}")
    public ResponseEntity create(@CurrentUser CustomUserDetails authUser,
                                 @PathVariable Long productId,
                                 @RequestParam("reviewContent")String reviewContent,@RequestParam("star")int star,
                                  ReviewImg reviewImg){
        log.info(" post 맵핑 실행 ");
        Long userId = authUser.getUser().getUserId();
        log.info(" 유저 정보 완료 ");
        Review saveReview = reviewService.create(userId,productId,reviewContent,star,reviewImg);
        log.info(" getListCategory 실행 ");
        return new ResponseEntity<>(new SingleResponseDto<>(reviewMapper.reviewToReviewResponseDto(saveReview)), HttpStatus.CREATED);
    }

    @PatchMapping("/review/{reviewId}")
    public ResponseEntity update(@PathVariable Long reviewId,
                                 @CurrentUser CustomUserDetails authUser,
                                 @RequestParam("reviewContent")String reviewContent,@RequestParam("star")int star,
                                 @RequestParam("delete")String delete,
                                 ReviewImg reviewImg){
        log.info("update 맵핑 실행");

        Long userId = authUser.getUser().getUserId();
        log.info("userId : ",userId);

        Review response = reviewService.update(reviewId,userId,reviewContent,star,reviewImg,delete);
        log.info("response : ",response);
        return new ResponseEntity<>(new SingleResponseDto<>(reviewMapper.reviewToReviewResponseDto(response)),HttpStatus.OK);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Long> delete(@PathVariable Long reviewId){
        log.info("delete 맵핑 실행 ");
        reviewService.delete(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/review/read/{reviewId}")
    public ResponseEntity getRead(@PathVariable Long reviewId){
        log.info("getRead 맵핑 실행 ");
        Review read = reviewService.getRead(reviewId);
        log.info("read : ",read);
        return new ResponseEntity<>(new SingleResponseDto<>(reviewMapper.reviewToReviewResponseDto(read)),HttpStatus.OK);
    }

    @GetMapping("/user/review")
    public ResponseEntity getLest(@CurrentUser CustomUserDetails authUser, @RequestParam int page){
        log.info("getLest 실행 ");
        int size =15;
        if (Objects.isNull(authUser)) {
            log.info("유저 정보가 없음");
            return ResponseEntity.ok().build();
        }
        Long userId = authUser.getUser().getUserId();
        log.info("userId : ",userId);
        Page<Review> reviewPage = reviewService.getList(userId, page-1, size);
        log.info("reviewPage :",reviewPage);
        List<Review> content = reviewPage.getContent();
        log.info("content : ",content);
        return new ResponseEntity(new MultiResponse<>(reviewMapper.reviewsToReviewResponseDto(content),reviewPage),HttpStatus.OK);
    }

    @GetMapping("/review/{productId}")
    public ResponseEntity getLestProduct(@PathVariable Long productId, @RequestParam int page){
        int size = 15;
        log.info("getLestProduct 실행 ");
        Page<Review> reviews = reviewService.getListProduct(productId, page-1, size);
        log.info("reviews : " , reviews);
        List<Review> content = reviews.getContent();
        log.info("content : ",content);

        return new ResponseEntity(new MultiResponse<>(reviewMapper.reviewsToReviewResponseDto(content),reviews),HttpStatus.OK);
    }


    @GetMapping("/review/seller")
    public ResponseEntity getProductReview(@RequestParam int page){
        int size = 15;
//        Long userId = authUser.getUser().getUserId();
        Page<Review> reviews = reviewService.getProductReview(page-1, size);
        List<Review> content = reviews.getContent();
        return new ResponseEntity(new MultiResponse<>(reviewMapper.reviewsToReviewResponseDto(content),reviews),HttpStatus.OK);
    }
}
