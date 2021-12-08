package com.shake_match.alchomist.ingredient.converter;

import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.ingredient.dto.IngredientRequest;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter {

    public Ingredient converterIngredient(IngredientRequest request) { // dto -> entity
        return Ingredient.builder()
                .name(request.getIngredientName())
                .cocktails(request.getCocktails())
                .build();
    }
}
