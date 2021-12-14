package com.shake_match.alchomist.ingredient.dto.response;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.ingredient.Ingredient;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDetailResponse {

    private Long ingredientId;
    private String ingredientName;
    private List<Cocktail> cocktails;
    private String type;
    private boolean isAlcohol;
    private String measure;

    public IngredientDetailResponse(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.ingredientName = ingredient.getName();
        this.cocktails = ingredient.getCocktails();
        this.type = ingredient.getType();
        this.measure = ingredient.getMeasure();
        this.isAlcohol = ingredient.isAlcohol();
    }
}
