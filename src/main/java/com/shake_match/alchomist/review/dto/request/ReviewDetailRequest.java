package com.shake_match.alchomist.review.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.users.Users;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReviewDetailRequest {

    private Users user;
    private Cocktail cocktail;
    private String url;
    private String description;
    private int rating;
}
