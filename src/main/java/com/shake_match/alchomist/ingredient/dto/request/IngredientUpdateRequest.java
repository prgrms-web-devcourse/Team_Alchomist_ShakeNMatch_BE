package com.shake_match.alchomist.ingredient.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import lombok.Getter;

import java.util.List;

@Getter
public class IngredientUpdateRequest {

    private String ingredientName;
    private List<Cocktail> cocktails;
    private String type;
    private boolean isAlcohol;
    private String measure;
}
