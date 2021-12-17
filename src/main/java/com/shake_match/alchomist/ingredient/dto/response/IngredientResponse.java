package com.shake_match.alchomist.ingredient.dto.response;

import lombok.Getter;

@Getter
public class IngredientResponse {

    private String name;
    public IngredientResponse(String name) {
        this.name = name;
    }
}
