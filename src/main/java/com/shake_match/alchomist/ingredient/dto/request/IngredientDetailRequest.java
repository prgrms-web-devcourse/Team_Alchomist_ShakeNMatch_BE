package com.shake_match.alchomist.ingredient.dto.request;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class IngredientDetailRequest {

    private String name;
    private String type;
    private List<Cocktail> cocktails;
    private boolean alcohol;
    private String measure;
}
