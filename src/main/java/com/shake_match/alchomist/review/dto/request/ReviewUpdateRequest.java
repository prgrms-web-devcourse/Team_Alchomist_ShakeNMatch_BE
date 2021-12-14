package com.shake_match.alchomist.review.dto.request;

public class ReviewUpdateRequest {
    private int rating;
    private String description;
    private String type;

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
}
