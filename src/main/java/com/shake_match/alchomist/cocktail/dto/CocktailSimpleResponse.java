package com.shake_match.alchomist.cocktail.dto;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.domain.Volume;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CocktailSimpleResponse {

    Long id;

    String name;

    String recipe;

    String type;

    int likes;

    float totalRating;

    List<Volume> ingredients;

    public CocktailSimpleResponse(Long id, String name, String recipe, String type, int likes,
                                  float totalRating) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
        this.type = type;
        this.likes = likes;
        this.totalRating = totalRating;
    }

    public CocktailSimpleResponse(Cocktail cocktail) {
        this.id = cocktail.getId();
        this.name = cocktail.getName();
        this.recipe = cocktail.getRecipe();
        this.type = cocktail.getType();
        this.likes = cocktail.getLikes();
        this.totalRating = cocktail.getTotalRating();
        this.ingredients = cocktail.getVolumes();
    }
}
