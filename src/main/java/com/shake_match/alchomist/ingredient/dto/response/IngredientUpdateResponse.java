package com.shake_match.alchomist.ingredient.dto.response;

import com.shake_match.alchomist.ingredient.Ingredient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientUpdateResponse {

    private String name;
    private String type;
    private boolean alcohol;
    private String measure;

    public IngredientUpdateResponse(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
        this.alcohol = ingredient.isAlcohol();
        this.measure = ingredient.getMeasure();
    }
}
