package com.shake_match.alchomist.review.dto.request;

import lombok.Getter;

@Getter
public class ReviewUpdateRequest {
    private int rating;
    private String description;
    private String url;
}
