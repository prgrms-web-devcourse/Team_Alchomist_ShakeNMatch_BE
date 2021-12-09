package com.shake_match.alchomist.ingredient.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;

import java.util.List;

public class IngredientUpdateRequest {

    private String ingredientName;
    private List<Cocktail> cocktails;

    public String getIngredientName() {
        return this.ingredientName;
    }

    public List<Cocktail> getCocktails() {
        return this.cocktails;
    }
}
