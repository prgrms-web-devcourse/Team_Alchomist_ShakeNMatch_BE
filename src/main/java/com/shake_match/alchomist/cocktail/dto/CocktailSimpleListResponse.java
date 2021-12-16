package com.shake_match.alchomist.cocktail.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class CocktailSimpleListResponse {

    private List<CocktailSimpleResponse> cocktails;

    public CocktailSimpleListResponse(
        List<CocktailSimpleResponse> cocktails) {
        this.cocktails = cocktails;
    }
}
