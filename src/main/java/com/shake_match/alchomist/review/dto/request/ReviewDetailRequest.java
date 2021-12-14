package com.shake_match.alchomist.review.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.users.Users;

public class ReviewDetailRequest {

    private Users users;
    private Cocktail cocktail;
    private String imageUrl;
    private String description;
    private int rating;

    public Users getUsers() {
        return users;
    }

    public Cocktail getCocktail() {
        return cocktail;
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

    public ReviewDetailRequest(Users users, Cocktail cocktail, String imageUrl, String description, int rating) {
        this.users = users;
        this.cocktail = cocktail;
        this.imageUrl = imageUrl;
        this.description = description;
        this.rating = rating;
    }
}
