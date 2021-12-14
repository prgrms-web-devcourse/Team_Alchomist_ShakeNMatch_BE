package com.shake_match.alchomist.review.dto.response;

import com.shake_match.alchomist.review.Review;
import lombok.Getter;

@Getter
public class ReviewUpdateResponse {

    private int rating;
    private String description;
    private String type;

    public ReviewUpdateResponse(Review review) {
        this.rating = review.getRating();
        this.description = review.getDescription();
        this.type = review.getType();
    }
}
