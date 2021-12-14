package com.shake_match.alchomist.review.converter;

import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.dto.request.ReviewDetailRequest;
import com.shake_match.alchomist.review.dto.request.ReviewImageRequest;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public Review converterReviewDetail(ReviewDetailRequest request){ // Dto -> Entity
        return Review.builder()
                .rating(request.getRating())
                .description(request.getDescription())
                .url(request.getImageUrl())
                .users(request.getUser())
                .cocktails(request.getCocktail())
                .build();
    }

    public Review converterReviewImage(ReviewImageRequest request){ // Dto -> Entity
        return Review.builder()
                .rating(request.getRating())
                .description(request.getDescription())
                .url(request.getImageUrl())
                .users(request.getUser())
                .cocktails(request.getCocktail())
                .build();
    }
}
