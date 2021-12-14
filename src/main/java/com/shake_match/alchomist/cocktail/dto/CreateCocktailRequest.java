package com.shake_match.alchomist.cocktail.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCocktailRequest {

    private String name;
    private String recipe;
    private String Type;
    private String youtubeLink;
    private List<String> theme;
    private List<String> ingredient;

    public CreateCocktailRequest(String name, String recipe, String Type, String youtubeLink) {
        this.name = name;
        this.recipe = recipe;
        this.Type = Type;
        this.youtubeLink = youtubeLink;
    }
}
