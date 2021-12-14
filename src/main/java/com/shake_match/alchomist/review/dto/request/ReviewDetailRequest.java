package com.shake_match.alchomist.review.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.users.Users;
import lombok.Getter;

@Getter
public class ReviewDetailRequest {

    private Users user;
    private Cocktail cocktail;
    private String type;
    private String description;
    private int rating;

    public ReviewDetailRequest(Users user, Cocktail cocktail, String type, String description, int rating) {
        this.user = user;
        this.cocktail = cocktail;
        this.type = type;
        this.description = description;
        this.rating = rating;
    }
}
