package com.shake_match.alchomist.ingredient.controller;

import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.ingredient.dto.response.IngredientDetailResponse;
import com.shake_match.alchomist.ingredient.service.IngredientService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredient/{id}")
    public ApiResponse<List<IngredientDetailResponse>> findAllById(Pageable pageable, @PathVariable("id") Long ingredientId) throws NotFoundException {
        return ApiResponse.ok(ingredientService.findAllById(pageable, ingredientId));
    }

    @GetMapping("/ingredient/{name}")
    public ApiResponse<List<IngredientDetailResponse>> findAllByName(Pageable pageable, @PathVariable("name") String ingredientName) throws NotFoundException {
        return ApiResponse.ok(ingredientService.findAllByName(pageable, ingredientName));
    }
}
