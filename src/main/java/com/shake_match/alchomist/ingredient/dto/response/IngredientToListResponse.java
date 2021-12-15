package com.shake_match.alchomist.ingredient.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class IngredientToListResponse {

    private final List<IngredientListResponse> ingredientListResponseList;

    public IngredientToListResponse(
        List<IngredientListResponse> ingredientListResponseList) {
        this.ingredientListResponseList = ingredientListResponseList;
    }
}
