package com.shake_match.alchomist.cocktail.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateCocktailRequest {

    private String name;
    private String recipe;
    private String type;
    private List<String> theme;
    private List<String> ingredient;

    public CreateCocktailRequest(String name, String recipe, String type) {
        this.name = name;
        this.recipe = recipe;
        this.type = type;
    }
}
