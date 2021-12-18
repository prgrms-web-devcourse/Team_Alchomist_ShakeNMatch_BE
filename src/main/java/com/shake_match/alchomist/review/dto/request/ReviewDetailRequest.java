package com.shake_match.alchomist.review.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewDetailRequest {

    private Long userId;
    private Long cocktailId;
    private String url;
    private String description;
    private int rating;
    private String nickname;
    private String cocktailName;
}
