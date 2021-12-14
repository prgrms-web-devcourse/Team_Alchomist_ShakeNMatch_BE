package com.shake_match.alchomist.ingredient.controller;

import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.ingredient.dto.request.IngredientDetailRequest;
import com.shake_match.alchomist.ingredient.dto.request.IngredientUpdateRequest;
import com.shake_match.alchomist.ingredient.dto.response.IngredientDetailResponse;
import com.shake_match.alchomist.ingredient.repository.IngredientRepository;
import com.shake_match.alchomist.ingredient.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientService ingredientService, IngredientRepository ingredientRepository) {
        this.ingredientService = ingredientService;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/ingredient/{id}") // 재료 id 조회
    public ApiResponse<IngredientDetailResponse> findById(@PathVariable("id") Long ingredientId) throws NotFoundException {
        return ApiResponse.ok(ingredientService.findById(ingredientId));
    }

    @GetMapping("/ingredient/{name}") // 재료 이름 조회
    public ApiResponse<IngredientDetailResponse> findByName(@PathVariable("name") String ingredientName) throws NotFoundException {
        return ApiResponse.ok(ingredientService.findByName(ingredientName));
    }

    @GetMapping("/ingredient")
    public Page<IngredientDetailResponse> findAll(Pageable pageable) {
        return ingredientRepository.findAll(pageable)
                .map(IngredientDetailResponse::new);
    }

    @PostMapping("/ingredient") // 재료 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<IngredientDetailResponse> insert(@RequestBody IngredientDetailRequest request) throws NotFoundException {
        return ApiResponse.ok(ingredientService.save(request));
    }

    @DeleteMapping("/ingredient/{id}") // 재료 삭제
    public ApiResponse<String> delete(@PathVariable("id") Long ingredientId) throws Exception {
        ingredientService.deleteOneById(ingredientId);
        return ApiResponse.ok("재료가 삭제되었습니다.");
    }

    @PutMapping("/ingredient/{id}") // 재료 수정
    public ApiResponse<String> updateByIngredientId(@PathVariable Long id, @RequestBody IngredientUpdateRequest request) throws Exception {
        ingredientService.updateById(id, request);
        return ApiResponse.ok("재료가 수정되었습니다.");
    }
}
