package com.shake_match.alchomist.review.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.users.Users;
import lombok.Getter;

@Getter
public class ReviewDetailRequest {

    private Users user;
    private Cocktail cocktail;
    private String imageUrl;
    private String description;
    private int rating;

    public ReviewDetailRequest(Users user, Cocktail cocktail, String imageUrl, String description, int rating) {
        this.user = user;
        this.cocktail = cocktail;
        this.imageUrl = imageUrl;
        this.description = description;
        this.rating = rating;
    }
}
