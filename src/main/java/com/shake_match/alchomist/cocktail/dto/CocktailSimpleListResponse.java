package com.shake_match.alchomist.cocktail.dto;

import com.shake_match.alchomist.ingredient.dto.response.IngredientResponse;
import java.util.List;
import lombok.Getter;

@Getter
public class CocktailSimpleListResponse {

    private List<CocktailSimpleResponse> cocktails;

    private List<IngredientResponse> ingredientResponses;

    public CocktailSimpleListResponse(
        List<CocktailSimpleResponse> cocktails,
        List<IngredientResponse> ingredientResponses) {
        this.cocktails = cocktails;
        this.ingredientResponses = ingredientResponses;
    }
}
