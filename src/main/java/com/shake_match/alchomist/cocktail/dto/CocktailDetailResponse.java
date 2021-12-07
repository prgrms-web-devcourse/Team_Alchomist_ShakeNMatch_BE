package com.shake_match.alchomist.cocktail.dto;

import com.shake_match.alchomist.cocktail.domain.Volume;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.theme.Theme;

import java.util.List;

public class CocktailDetailResponse {

    Long id;

    private String name;

    List<Volume> volumes;

    List<Theme> themes;

    List<Review> reviews;

    String recipe;

    String imageUrl;

    String youtubeLink;

    int likes;

    float totalRating;

    public CocktailDetailResponse(Long id, String name, List<Volume> volumes, List<Theme> themes, List<Review> reviews, String recipe, String imageUrl, String youtubeLink, int likes, float totalRating) {
        this.id = id;
        this.name = name;
        this.volumes = volumes;
        this.themes = themes;
        this.reviews = reviews;
        this.recipe = recipe;
        this.imageUrl = imageUrl;
        this.youtubeLink = youtubeLink;
        this.likes = likes;
        this.totalRating = totalRating;
    }
}
