package com.shake_match.alchomist.review.converter;

import com.shake_match.alchomist.cocktail.Cocktail;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.dto.ReviewRequest;
import com.shake_match.alchomist.users.Users;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public Review converterReview(ReviewRequest reviewRequest, Users users, Cocktail cocktail){ // Dto -> Entity
        return Review.builder()
                .rating(reviewRequest.getRating())
                .description(reviewRequest.getDescription())
                .url(reviewRequest.getImageUrl())
                .users(users)
                .cocktails(cocktail)
                .build();
    }
}
