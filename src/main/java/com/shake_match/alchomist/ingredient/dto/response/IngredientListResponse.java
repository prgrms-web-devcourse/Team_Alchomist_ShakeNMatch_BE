package com.shake_match.alchomist.ingredient.dto.response;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class IngredientListResponse {

    private final Long id;
    private final String name;
    private final String type;
    private final boolean isAlcohol;
    private final String measure;
    private List<Cocktail> cocktails = new ArrayList<>();

    public IngredientListResponse(Long id, String name, String type, boolean isAlcohol,
                                  String measure,
                                  List<Cocktail> cocktails) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isAlcohol = isAlcohol;
        this.measure = measure;
        this.cocktails = cocktails;
    }
}
