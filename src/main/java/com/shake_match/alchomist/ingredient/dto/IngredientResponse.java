package com.shake_match.alchomist.ingredient.dto;

import com.shake_match.alchomist.cocktail.Cocktail;
import com.shake_match.alchomist.ingredient.Ingredient;

public class IngredientResponse {

    private Long ingredientId;
    private String ingredientName;
    private Long cocktailId;
    private String cocktailName;
    private String imageUrl;

    public IngredientResponse(Cocktail cocktail) {
        this.cocktailId = cocktail.getId();
        this.cocktailName = cocktail.getName();
        this.imageUrl = cocktail.getImageUrl();
    }
}
