package com.shake_match.alchomist.ingredient.dto;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import lombok.Builder;

import java.util.List;

@Builder
public class IngredientRequest {
    private Long ingredientId;
    private String ingredientName;
    private List<Cocktail> cocktails;

    public Long getIngredientId() {
        return this.ingredientId;
    }

    public String getIngredientName() {
        return this.ingredientName;
    }

    public List<Cocktail> getCocktails() {
        return this.cocktails;
    }
}
