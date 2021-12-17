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

    private Long id;
    private String name;
    private String type;
    private boolean alcohol;
    private String measure;

    public IngredientDetailResponse(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.type = ingredient.getType();
        this.measure = ingredient.getMeasure();
        this.alcohol = ingredient.isAlcohol();
    }
}
