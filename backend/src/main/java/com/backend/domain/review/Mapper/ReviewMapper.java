package com.backend.domain.review.Mapper;

import com.backend.domain.review.domain.Review;
import com.backend.domain.review.dto.ReviewPatchDto;
import com.backend.domain.review.dto.ReviewPostDto;
import com.backend.domain.review.dto.ReviewResponseDto;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review reviewPostDtoToReview(ReviewPostDto reviewPostDto);

    Review reviewPatchDtoToReview(ReviewPatchDto reviewPatchDto);

    ReviewResponseDto reviewToReviewResponseDto(Review review);

    List<ReviewResponseDto> reviewsToReviewResponseDto(List<Review> reviews);
}
