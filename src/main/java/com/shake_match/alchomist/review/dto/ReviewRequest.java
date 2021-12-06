package com.shake_match.alchomist.review.dto;

public class ReviewRequest {

    private Long userId;
    private Long cocktailId;
    private String imageUrl;
    private String description;
    private int rating;

    public Long getUserId() {
        return userId;
    }

    public Long getCocktailId() {
        return cocktailId;
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
