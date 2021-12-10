package com.shake_match.alchomist.review.dto;

import com.shake_match.alchomist.review.Review;

public class ReviewResponse {

    private Long reviewId;
    private Long userId;
    private Long cocktailId;
    private String imageUrl;
    private String description;
    private int rating;

    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.userId = review.getUsers().getId();
        this.cocktailId = review.getCocktails().getId();
        this.imageUrl = review.getUrl();
        this.description = review.getDescription();
        this.rating = review.getRating();
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCocktailId() {
        return cocktailId;
    }
}
