package com.shake_match.alchomist.ingredient.dto;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.ingredient.Ingredient;

import java.util.List;

public class IngredientResponse {

    private Long ingredientId;
    private String ingredientName;
    private List<Cocktail> cocktails;
//    private Long cocktailId;
//    private String cocktailName;
//    private String imageUrl;

    public IngredientResponse(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.ingredientName = ingredient.getName();
        this.cocktails = ingredient.getCocktails();
//        this.cocktailId = ingredient.getCocktails()
//                .stream()
//                .filter(x -> x.getId().equals(cocktailId))
//                .findFirst().get().getId();
//        this.cocktailName = ingredient.getCocktails()
//                .stream()
//                .filter(x -> x.getName().equals(cocktailName))
//                .findFirst().get().getName();
//        this.imageUrl = ingredient.getCocktails()
//                .stream()
//                .filter(x -> x.getImageUrl().equals(imageUrl))
//                .findFirst().get().getImageUrl();
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }
}
