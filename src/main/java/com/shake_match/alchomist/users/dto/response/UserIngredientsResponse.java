package com.shake_match.alchomist.users.dto.response;

import com.shake_match.alchomist.ingredient.Ingredient;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class UserIngredientsResponse {

    List<Ingredient> ingredients = new ArrayList<>();

    public UserIngredientsResponse(
        List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
