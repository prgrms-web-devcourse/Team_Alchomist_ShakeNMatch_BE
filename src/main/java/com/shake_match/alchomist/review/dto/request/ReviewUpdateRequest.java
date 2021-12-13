package com.shake_match.alchomist.review.dto.request;

import javax.persistence.Column;

public class ReviewUpdateRequest {

    private Long reviewId;
    private int rating;
    private String description;
    private String url;

    public Long getReviewId() {
        return reviewId;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
