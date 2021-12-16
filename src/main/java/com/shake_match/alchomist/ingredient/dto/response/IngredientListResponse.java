package com.shake_match.alchomist.ingredient.dto.response;

import lombok.Getter;

@Getter
public class IngredientListResponse {

    private final Long id;
    private final String name;
    private final String type;
    private final boolean isAlcohol;
    private final String measure;

    public IngredientListResponse(Long id, String name, String type, boolean isAlcohol,
                                  String measure) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isAlcohol = isAlcohol;
        this.measure = measure;
    }
}
