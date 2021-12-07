package com.shake_match.alchomist.cocktail.service;

import com.shake_match.alchomist.cocktail.dto.CocktailDetailResponse;
import com.shake_match.alchomist.cocktail.dto.CreateCocktailRequest;
import com.shake_match.alchomist.cocktail.dto.SearchResponse;

import java.util.List;

public interface CocktailService {

    CocktailDetailResponse searchDetail(Long id) throws Exception;
    List<SearchResponse> searchByTheme(String mainCategory, String subCategory) throws Exception;
    CocktailDetailResponse searchByName(String name) throws Exception;
}
