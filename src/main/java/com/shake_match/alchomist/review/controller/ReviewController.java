package com.shake_match.alchomist.review.controller;

import com.shake_match.alchomist.cocktail.Cocktail;
import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.review.dto.ReviewRequest;
import com.shake_match.alchomist.review.dto.ReviewResponse;
import com.shake_match.alchomist.review.service.ReviewService;
import com.shake_match.alchomist.users.Users;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review") // 리뷰 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResponse> insert(@RequestBody ReviewRequest request, Users user, Cocktail cocktail) throws NotFoundException {
        return ApiResponse.ok(reviewService.insert(request, user, cocktail));
    }

    // 칵테일 id를 통한 조회


    // 사용자 id를 통한 조회


    @DeleteMapping("/review/{id}") // 리뷰 삭제
    public ApiResponse<Void> delete(@PathVariable("id") Long reviewId, Users user) throws Exception {
        reviewService.delete(reviewId, user);
        return ApiResponse.ok(null);
    }
}
