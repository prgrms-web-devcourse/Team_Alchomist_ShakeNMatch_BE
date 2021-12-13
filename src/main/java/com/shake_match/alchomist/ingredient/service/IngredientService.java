package com.shake_match.alchomist.ingredient.service;

import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.global.ErrorCode;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.ingredient.converter.IngredientConverter;
import com.shake_match.alchomist.ingredient.dto.request.IngredientDetailRequest;
import com.shake_match.alchomist.ingredient.dto.response.IngredientDetailResponse;
import com.shake_match.alchomist.ingredient.dto.request.IngredientUpdateRequest;
import com.shake_match.alchomist.ingredient.dto.response.IngredientUpdateResponse;
import com.shake_match.alchomist.ingredient.repository.IngredientRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientConverter ingredientConverter; // insert문 빼고는 쓸 일이 없을듯

    public IngredientService(IngredientRepository ingredientRepository, IngredientConverter ingredientConverter) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientConverter = ingredientConverter;
    }

    @Transactional // ingredient 저장
    public IngredientDetailResponse save(IngredientDetailRequest request) {
        Ingredient response = ingredientConverter.converterIngredient(request);
        ingredientRepository.save(response);
        return new IngredientDetailResponse(response);
    }

    @Transactional // ingredient 전체 조회
    public List<IngredientDetailResponse> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(IngredientDetailResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional // ingredientId로 조회
    public IngredientDetailResponse findById(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_INGREDIENT));
        return new IngredientDetailResponse(ingredient);
    }

    @Transactional // ingredientName으로 조회
    public IngredientDetailResponse findByName(String name) {
        Ingredient ingredient = ingredientRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_INGREDIENT));
        return new IngredientDetailResponse(ingredient);
    }

    @Transactional // ingredient 삭제
    public void deleteOneById(Long ingredientId) throws Exception {
        if (!ingredientRepository.existsById(ingredientId)) {
            throw new NotFoundException(ErrorCode.NOT_EXIST_INGREDIENT);
        }
        ingredientRepository.deleteById(ingredientId);
    }

    @Transactional // ingredient 수정
    public IngredientUpdateResponse updateById(Long ingredientId, IngredientUpdateRequest request) throws Exception {
        Ingredient ingredient = ingredientRepository.findById(ingredientId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_INGREDIENT));
        ingredient.update(request);
        return new IngredientUpdateResponse(ingredient);
    }
}
