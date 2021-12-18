package com.shake_match.alchomist.review.controller;


import com.shake_match.alchomist.amazon.service.S3Service;
import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.review.dto.request.ReviewDetailRequest;
import com.shake_match.alchomist.review.dto.request.ReviewUpdateRequest;
import com.shake_match.alchomist.review.dto.response.ReviewDetailResponse;
import com.shake_match.alchomist.review.dto.response.ReviewUpdateResponse;
import com.shake_match.alchomist.review.repository.ReviewRepository;
import com.shake_match.alchomist.review.service.ReviewService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final S3Service s3Service;

    public ReviewController(ReviewService reviewService, S3Service s3Service) {
        this.reviewService = reviewService;
        this.s3Service = s3Service;
    }

    @PostMapping // 리뷰 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewDetailResponse> insert(@RequestPart(value = "request") ReviewDetailRequest request, @RequestPart(value = "file") MultipartFile file) throws IOException {
        s3Service.upload(file);
        return ApiResponse.ok(reviewService.insert(request));
    }

    // 사용자 id를 통한 조회
    @GetMapping("/{id}")
    public ApiResponse<List<ReviewDetailResponse>> findAllByUserId(@PathVariable("id") Long userId, Pageable pageable) throws NotFoundException {
        return ApiResponse.ok(reviewService.findAllByUserId(pageable, userId));
    }

    // 칵테일 id를 통한 조회
    @GetMapping("/cocktailId")
    public ApiResponse<List<ReviewDetailResponse>> findAllByCocktailId(@RequestParam Long cocktailId) throws NotFoundException {
        return ApiResponse.ok(reviewService.findAllByCocktailId(cocktailId));
    }

    @DeleteMapping("/{id}") // 리뷰 삭제
    public ApiResponse<String> delete(@PathVariable("id") Long id) throws Exception {
        reviewService.delete(id);
        return ApiResponse.ok("리뷰가 삭제되었습니다.");
    }
    

    @PutMapping("/{id}") // 리뷰 수정
    public ApiResponse<ReviewUpdateResponse> updateByReviewId(@PathVariable("id") Long id, @RequestBody ReviewUpdateRequest request) throws Exception {
        return ApiResponse.ok(reviewService.updateById(id, request));
    }

    @GetMapping
    public ApiResponse<List<ReviewDetailResponse>> findAll() throws NotFoundException {
        return ApiResponse.ok(reviewService.findAll());
    }
}
