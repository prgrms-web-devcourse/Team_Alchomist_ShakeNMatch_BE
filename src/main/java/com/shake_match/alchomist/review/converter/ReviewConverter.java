package com.shake_match.alchomist.review.converter;

import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.dto.ReviewDto;
import com.shake_match.alchomist.review.dto.request.ReviewDetailRequest;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    private final String bucket = "team15-image-bucket";
    private final String region = "ap-northeast-2";

    public Review converterReviewDetail(ReviewDetailRequest request) { // Dto -> Entity
        return Review.builder()
                .rating(request.getRating())
                .description(request.getDescription())
                .url("https://" + bucket + ".s3." + region + ".amazonaws.com/" + request.getUrl())
                .userId(request.getUserId())
                .cocktailId(request.getCocktailId())
                .build();
    }

    public static ReviewDto toReviewDto(Review review) {
        return new ReviewDto(
                review.getRating(),
                review.getDescription(),
                review.getUrl(),
                review.getUserId(),
                review.getCocktailId()
        );
    }
}
