package com.shake_match.alchomist.ingredient.converter;

import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.ingredient.dto.request.IngredientDetailRequest;
import com.shake_match.alchomist.ingredient.dto.response.IngredientResponse;
import com.shake_match.alchomist.ingredient.dto.response.IngredientListResponse;
import com.shake_match.alchomist.ingredient.dto.response.IngredientToListResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter {

    public Ingredient converterIngredient(IngredientDetailRequest request) { // dto -> entity
        return Ingredient.builder()
                .name(request.getIngredientName())
                .isAlcohol(request.isAlcohol())
                .type(request.getType())
                .measure(request.getMeasure())
                .build();
    }

    // entity -> dto
    public IngredientResponse converterIngredientResponse(Ingredient ingredient) {
        return new IngredientResponse(ingredient.getName());
    }

    public IngredientListResponse converterIngredientListResponse(Ingredient ingredient) {
        return new IngredientListResponse(ingredient.getId(), ingredient.getName(),
            ingredient.getType(), ingredient.isAlcohol(), ingredient.getMeasure());
    }

    public IngredientToListResponse converterIngredientToListResponse(
        List<IngredientListResponse> ingredientListResponseList) {
        return new IngredientToListResponse(ingredientListResponseList);
    }


}
