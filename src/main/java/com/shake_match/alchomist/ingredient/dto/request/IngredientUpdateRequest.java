package com.shake_match.alchomist.ingredient.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import lombok.Getter;

import java.util.List;

@Getter
public class IngredientUpdateRequest {

    private String name;
    private String type;
    private boolean alcohol;
    private String measure;
}
