package com.shake_match.alchomist.ingredient.dto.response;

import com.shake_match.alchomist.ingredient.Ingredient;
import lombok.Getter;

@Getter
public class IngredientResponse {

    private Long id;
    private String name;
    private String type;
    private boolean alcohol;
    private String measure;

    public IngredientResponse(Long id, String name, String type, boolean alcohol,
                              String measure) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.alcohol = alcohol;
        this.measure = measure;
    }
}
