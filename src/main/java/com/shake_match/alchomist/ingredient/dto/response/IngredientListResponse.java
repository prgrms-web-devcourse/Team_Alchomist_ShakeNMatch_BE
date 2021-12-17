package com.shake_match.alchomist.ingredient.dto.response;

import lombok.Getter;

@Getter
public class IngredientListResponse {

    private final Long id;
    private final String name;
    private final String type;
    private final boolean alcohol;
    private final String measure;

    public IngredientListResponse(Long id, String name, String type, boolean alcohol,
                                  String measure) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.alcohol = alcohol;
        this.measure = measure;
    }
}
