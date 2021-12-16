package com.shake_match.alchomist.ingredient.dto.response;

import com.shake_match.alchomist.ingredient.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDetailResponse {

    private Long ingredientId;
    private String ingredientName;
    private String type;
    private boolean isAlcohol;
    private String measure;

    public IngredientDetailResponse(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.ingredientName = ingredient.getName();
        this.type = ingredient.getType();
        this.measure = ingredient.getMeasure();
        this.isAlcohol = ingredient.isAlcohol();
    }
}
