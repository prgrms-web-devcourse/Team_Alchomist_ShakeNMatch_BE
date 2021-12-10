package com.shake_match.alchomist.review.controller;


import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.review.dto.ReviewRequest;
import com.shake_match.alchomist.review.dto.ReviewResponse;
import com.shake_match.alchomist.review.service.ReviewService;
import com.shake_match.alchomist.users.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping // 리뷰 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResponse> insert(@RequestBody ReviewRequest request) throws NotFoundException {
        return ApiResponse.ok(reviewService.insert(request));
    }

    // 칵테일 id를 통한 조회
    @GetMapping("/{userId}")
    public ApiResponse<List<ReviewResponse>> findAllByUserId(Pageable pageable, @PathVariable("userId") Long userId) throws NotFoundException {
        return ApiResponse.ok(reviewService.findAllByUserId(pageable, userId));
    }

    // 사용자 id를 통한 조회
    @GetMapping("/{cocktailId}")
    public ApiResponse<List<ReviewResponse>> findAllByCocktailId(Pageable pageable, @PathVariable("cocktailId") Long cocktailId) throws NotFoundException {
        return ApiResponse.ok(reviewService.findAllByCocktailId(pageable, cocktailId));
    }

    @DeleteMapping("/{id}") // 리뷰 삭제
    public ApiResponse<Void> delete(@PathVariable("id") Long reviewId, Users user) throws Exception {
        reviewService.delete(reviewId, user);
        return ApiResponse.ok(null);
    }
}
