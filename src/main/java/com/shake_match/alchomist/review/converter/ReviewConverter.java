package com.shake_match.alchomist.review.converter;

import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.dto.request.ReviewDetailRequest;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public Review converterReview(ReviewDetailRequest request){ // Dto -> Entity
        return Review.builder()
                .rating(request.getRating())
                .description(request.getDescription())
                .url(request.getImageUrl())
                .users(request.getUsers())
                .cocktails(request.getCocktail())
                .build();
    }
}
