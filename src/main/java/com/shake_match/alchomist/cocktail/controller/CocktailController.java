package com.shake_match.alchomist.cocktail.controller;

import com.shake_match.alchomist.cocktail.dto.CocktailDetailResponse;
import com.shake_match.alchomist.cocktail.dto.SearchResponse;
import com.shake_match.alchomist.cocktail.service.CocktailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cocktail")
public class CocktailController {

    private final CocktailService service;

    @GetMapping("/theme")
    public List<SearchResponse> searchByTheme(@RequestParam String mainCategory, @RequestParam String subCategory) throws Exception{
        return service.searchByTheme(mainCategory, subCategory);
    }

    @GetMapping("/{name}")
    public CocktailDetailResponse searchCocktail(@PathVariable String name) throws Exception{
        return service.searchByName(name);
    }

    @GetMapping("/{id}")
    public CocktailDetailResponse searchCocktail(@PathVariable Long id) throws Exception {
        return service.searchDetail(id);
    }
}
