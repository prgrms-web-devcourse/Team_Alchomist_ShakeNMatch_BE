package com.shake_match.alchomist.cocktail.controller;

import com.shake_match.alchomist.amazon.service.S3Service;
import com.shake_match.alchomist.cocktail.dto.CocktailDetailResponse;
import com.shake_match.alchomist.cocktail.dto.CreateCocktailRequest;
import com.shake_match.alchomist.cocktail.dto.SearchResponse;
import com.shake_match.alchomist.cocktail.service.CocktailService;
import com.shake_match.alchomist.global.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cocktail")
public class CocktailController {

    private final CocktailService service;
    private final S3Service s3Service;

    @GetMapping("/theme") //테마에 따라서 칵테일 검색
    public ApiResponse<List<SearchResponse>> searchByTheme(@RequestParam String mainCategory, @RequestParam String subCategory) throws Exception{
        return ApiResponse.ok(service.searchByTheme(mainCategory, subCategory));
    }

    @GetMapping("/name") //칵테일 이름으로 상세 정보 검색
    public ApiResponse<CocktailDetailResponse> searchCocktail(@RequestParam String name) throws Exception{
        return ApiResponse.ok(service.searchByName(name));
    }

    @GetMapping("/id") //칵테일 아이디로 상세 정보 검색
    public ApiResponse<CocktailDetailResponse> searchCocktail(@RequestParam Long id) throws Exception {
        return ApiResponse.ok(service.searchDetail(id));
    }

    @GetMapping //칵테일 전체 검색
    public ApiResponse<List<SearchResponse>> searchAll() throws Exception {
        return ApiResponse.ok(service.searchAll());
    }

    @PostMapping //칵테일 정보 저장
    public ApiResponse<String> createCocktail(@RequestPart(value = "request") CreateCocktailRequest createCocktailRequest, @RequestPart(value = "file") MultipartFile file) throws Exception {
        s3Service.upload(file);
        return ApiResponse.ok(service.createCocktail(createCocktailRequest));
    }

    @DeleteMapping("/{name}") //칵테일 이름으로 삭제
    public ApiResponse<String> deleteCocktail(@PathVariable String name) throws Exception{
        return ApiResponse.ok(service.deleteCocktail(name));
    }
}
