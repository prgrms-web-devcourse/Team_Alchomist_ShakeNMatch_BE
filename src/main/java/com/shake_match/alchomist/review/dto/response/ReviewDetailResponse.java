package com.shake_match.alchomist.review.dto.response;

import com.shake_match.alchomist.review.Review;
import lombok.Getter;

@Getter
public class ReviewDetailResponse {

    private Long reviewId;
    private Long userId;
    private Long cocktailId;
    private String type;
    private String description;
    private int rating;

    public ReviewDetailResponse(Review review) {
        this.reviewId = review.getId();
        this.userId = review.getUsers().getId();
        this.cocktailId = review.getCocktails().getId();
        this.type = review.getType();
        this.description = review.getDescription();
        this.rating = review.getRating();
    }
}
