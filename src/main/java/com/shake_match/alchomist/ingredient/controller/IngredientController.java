package com.shake_match.alchomist.ingredient.controller;

import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.ingredient.dto.request.IngredientDetailRequest;
import com.shake_match.alchomist.ingredient.dto.request.IngredientUpdateRequest;
import com.shake_match.alchomist.ingredient.dto.response.IngredientDetailResponse;
import com.shake_match.alchomist.ingredient.dto.response.IngredientUpdateResponse;
import com.shake_match.alchomist.ingredient.repository.IngredientRepository;
import com.shake_match.alchomist.ingredient.service.IngredientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientRepository ingredientRepository;

    public IngredientController(IngredientService ingredientService, IngredientRepository ingredientRepository) {
        this.ingredientService = ingredientService;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/{ingredientId}") // 재료 id 조회
    public ApiResponse<IngredientDetailResponse> findById(@PathVariable("ingredientId") Long ingredientId) throws NotFoundException {
        return ApiResponse.ok(ingredientService.findById(ingredientId));
    }

    @GetMapping("/ingredientName") // 재료 이름 조회
    public ApiResponse<IngredientDetailResponse> findByName(@RequestParam String ingredientName) throws NotFoundException {
        return ApiResponse.ok(ingredientService.findByName(ingredientName));
    }

    @GetMapping
    public List<IngredientDetailResponse> findAll(Pageable pageable) {
        return ingredientRepository.findAll(pageable)
                .map(IngredientDetailResponse::new)
                .stream()
                .collect(Collectors.toList());
    }

    @PostMapping // 재료 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<IngredientDetailResponse> insert(@RequestBody IngredientDetailRequest request) throws NotFoundException {
        return ApiResponse.ok(ingredientService.save(request));
    }

    @DeleteMapping("/{id}") // 재료 삭제
    public ApiResponse<String> delete(@PathVariable("id") Long ingredientId) throws Exception {
        ingredientService.deleteOneById(ingredientId);
        return ApiResponse.ok("재료가 삭제되었습니다.");
    }

    @PutMapping("/{id}") // 재료 수정
    public ApiResponse<IngredientUpdateResponse> updateByIngredientId(@PathVariable("id") Long id, @RequestBody IngredientUpdateRequest request) throws Exception {
        return ApiResponse.ok(ingredientService.updateById(id, request));
    }
}
