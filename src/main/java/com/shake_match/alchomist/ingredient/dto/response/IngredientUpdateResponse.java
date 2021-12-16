package com.shake_match.alchomist.ingredient.dto.response;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.ingredient.Ingredient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IngredientUpdateResponse {

    private String ingredientName;
    private String type;
    private boolean isAlcohol;
    private String measure;

    public IngredientUpdateResponse(Ingredient ingredient) {
        this.ingredientName = ingredient.getName();
        this.type = ingredient.getType();
        this.isAlcohol = ingredient.isAlcohol();
        this.measure = ingredient.getMeasure();
    }
}
