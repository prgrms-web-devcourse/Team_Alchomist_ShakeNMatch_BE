package com.shake_match.alchomist.review.dto;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.users.Users;

public class ReviewRequest {

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
}
