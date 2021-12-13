package com.shake_match.alchomist.cocktail.dto;

import com.shake_match.alchomist.review.dto.ReviewDto;
import com.shake_match.alchomist.theme.dto.ThemeDto;
import lombok.Getter;

import java.util.List;

@Getter
public class CocktailDetailResponse {

    Long id;

    String name;

    List<VolumeDto> volumes;

    List<ThemeDto> themes;

    List<ReviewDto> reviews;

    String recipe;

    String imageUrl;

    String youtubeLink;

    int likes;

    float totalRating;

    public CocktailDetailResponse(Long id, String name, List<VolumeDto> volumes, List<ThemeDto> themes, List<ReviewDto> reviews, String recipe, String imageUrl, String youtubeLink, int likes, float totalRating) {
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
