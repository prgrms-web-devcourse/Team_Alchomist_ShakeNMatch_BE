package com.shake_match.alchomist.cocktail.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCocktailRequest {

    private String name;
    private String recipe;
    private String imageUrl;
    private String youtubeLink;

    public CreateCocktailRequest(String name, String recipe, String imageUrl, String youtubeLink) {
        this.name = name;
        this.recipe = recipe;
        this.imageUrl = imageUrl;
        this.youtubeLink = youtubeLink;
    }
}