package com.shake_match.alchomist.review.converter;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.dto.ReviewRequest;
import com.shake_match.alchomist.users.Users;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {

    public Review converterReview(ReviewRequest request){ // Dto -> Entity
        return Review.builder()
                .rating(request.getRating())
                .description(request.getDescription())
                .url(request.getImageUrl())
                .users(request.getUsers())
                .cocktails(request.getCocktail())
                .build();
    }
}
