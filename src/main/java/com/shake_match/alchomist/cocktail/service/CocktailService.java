package com.shake_match.alchomist.cocktail.service;

import com.shake_match.alchomist.cocktail.dto.CocktailDetailResponse;

import com.shake_match.alchomist.cocktail.dto.CreateCocktailRequest;
import com.shake_match.alchomist.cocktail.dto.SearchResponse;

import java.util.List;

public interface CocktailService {

    CocktailDetailResponse searchDetail(Long id) throws Exception;
    List<SearchResponse> searchByTheme(String mainCategory, String subCategory) throws Exception;
    List<SearchResponse> searchByName(String name) throws Exception;
    String createCocktail(CreateCocktailRequest createCocktailRequest) throws Exception;
    String deleteCocktail(String name) throws Exception;

    void cocktailandIngredient(Long cocktail_id, Long ingredient_id);

    List<SearchResponse> searchAll() throws Exception;
}
