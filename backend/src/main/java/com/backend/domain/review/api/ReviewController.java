package com.backend.domain.review.api;

import com.backend.domain.product.application.ImageUploadService;
import com.backend.domain.review.Mapper.ReviewMapper;
import com.backend.domain.review.application.ReviewService;
import com.backend.domain.review.domain.Review;
import com.backend.domain.review.dto.ReviewImg;
import com.backend.global.annotation.CurrentUser;
import com.backend.global.config.auth.userdetails.CustomUserDetails;
import com.backend.global.dto.Response.MultiResponse;
import com.backend.global.dto.Response.SingleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final ImageUploadService awsS3Service;

    @PostMapping("/test")
    public String test(ReviewImg reviewImg){
        if (reviewImg.getReviewImg().isEmpty()){
            return "이미지 없다";
        }else {
            return awsS3Service.StoreImage(reviewImg.getReviewImg());
        }

    }
    @PostMapping("/review/{productId}")
    public ResponseEntity create(@CurrentUser CustomUserDetails authUser,
                                 @PathVariable Long productId,
                                 @RequestParam("reviewContent")String reviewContent,@RequestParam("star")int star,
                                  ReviewImg reviewImg){
        String reviewUrl;
        if(reviewImg.getReviewImg().isEmpty()){
            reviewUrl = null;
        }
        else {
             reviewUrl = awsS3Service.StoreImage(reviewImg.getReviewImg());
        }
        Long userId = authUser.getUser().getUserId();

        Review saveReview = reviewService.create(userId,productId,reviewContent,star,reviewUrl);

        return new ResponseEntity<>(new SingleResponseDto<>(reviewMapper.reviewToReviewResponseDto(saveReview)), HttpStatus.CREATED);
    }

    @PatchMapping("/review/{reviewId}")
    public ResponseEntity update(@PathVariable Long reviewId,
                                 @CurrentUser CustomUserDetails authUser,
                                 @RequestParam("reviewContent")String reviewContent,@RequestParam("star")int star,
                                 ReviewImg reviewImg){
        String reviewUrl;
        if(reviewImg.getReviewImg().isEmpty()){
            reviewUrl = null;
        }
        else {
            reviewUrl = awsS3Service.StoreImage(reviewImg.getReviewImg());
        }
        Long userId = authUser.getUser().getUserId();

        Review response = reviewService.update(reviewId,userId,reviewContent,star,reviewUrl);

        return new ResponseEntity<>(new SingleResponseDto<>(reviewMapper.reviewToReviewResponseDto(response)),HttpStatus.OK);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Long> delete(@PathVariable Long reviewId){
        reviewService.delete(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/review/read/{reviewId}")
    public ResponseEntity getRead(@PathVariable Long reviewId){
        Review read = reviewService.getRead(reviewId);
        return new ResponseEntity<>(new SingleResponseDto<>(reviewMapper.reviewToReviewResponseDto(read)),HttpStatus.OK);
    }

    @GetMapping("/user/review")
    public ResponseEntity getLest(@CurrentUser CustomUserDetails authUser, @RequestParam int page){
        int size =15;
        if (Objects.isNull(authUser)) {
            return ResponseEntity.ok().build();
        }
        Long userId = authUser.getUser().getUserId();
        Page<Review> reviewPage = reviewService.getList(userId, page, size);
        List<Review> content = reviewPage.getContent();
        return new ResponseEntity(new MultiResponse<>(reviewMapper.reviewsToReviewResponseDto(content),reviewPage),HttpStatus.OK);
    }

    @GetMapping("/review/{productId}")
    public ResponseEntity getLestProduct(@PathVariable Long productId, @RequestParam int page){
        int size = 15;
        Page<Review> reviews = reviewService.getListProduct(productId, page, size);
        List<Review> content = reviews.getContent();

        return new ResponseEntity(new MultiResponse<>(reviewMapper.reviewsToReviewResponseDto(content),reviews),HttpStatus.OK);
    }

}
