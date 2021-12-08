package com.shake_match.alchomist.ingredient.service;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.ingredient.converter.IngredientConverter;
import com.shake_match.alchomist.ingredient.dto.IngredientRequest;
import com.shake_match.alchomist.ingredient.dto.IngredientResponse;
import com.shake_match.alchomist.ingredient.repository.IngredientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientConverter ingredientConverter; // insert문 빼고는 쓸 일이 없을듯
    private final CocktailRepository cocktailRepository;

    public IngredientService(IngredientRepository ingredientRepository, IngredientConverter ingredientConverter, CocktailRepository cocktailRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientConverter = ingredientConverter;
        this.cocktailRepository = cocktailRepository;
    }

    @Transactional
    public IngredientResponse save(IngredientRequest request) {
        Ingredient ingredient = ingredientConverter.converterIngredient(request);
        ingredientRepository.save(ingredient);
        return new IngredientResponse(ingredient);
    }

    @Transactional // ingredient 전체 조회
    public List<IngredientResponse> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(IngredientResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional // ingredientId로 조회
    public List<IngredientResponse> findAllById(Pageable pageable, Long ingredientId) {
        return ingredientRepository.findAll(pageable)
                .map(IngredientResponse::new)
                .stream().filter(x -> x.getIngredientId().equals(ingredientId))
                .collect(Collectors.toList());
    }

    @Transactional // ingredientName으로 조회
    public List<IngredientResponse> findAllByName(Pageable pageable, String name) {
        return ingredientRepository.findAll(pageable)
                .map(IngredientResponse::new)
                .stream().filter(x -> x.getIngredientName().equals(name))
                .collect(Collectors.toList());
    }

}
