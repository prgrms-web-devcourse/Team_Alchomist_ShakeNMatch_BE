package com.shake_match.alchomist.review.dto;

import com.shake_match.alchomist.review.Review;

public class ReviewResponse {

    private Long reviewId;
    private Long userId;
    private String imageUrl;
    private String description;
    private int rating;

    public ReviewResponse(Review review) {
        this.reviewId = review.getId();
        this.userId = review.getUsers().getId();
        this.imageUrl = review.getUrl();
        this.description = review.getDescription();
        this.rating = review.getRating();
    }

    public Long getReviewId() {
        return reviewId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }
}
