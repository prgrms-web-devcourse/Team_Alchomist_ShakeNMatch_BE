package com.shake_match.alchomist.review.controller;


import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.review.dto.request.ReviewDetailRequest;
import com.shake_match.alchomist.review.dto.request.ReviewUpdateRequest;
import com.shake_match.alchomist.review.dto.response.ReviewDetailResponse;
import com.shake_match.alchomist.review.dto.response.ReviewUpdateResponse;
import com.shake_match.alchomist.review.repository.ReviewRepository;
import com.shake_match.alchomist.review.service.ReviewService;
import com.shake_match.alchomist.users.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
    }

    @PostMapping("/review") // 리뷰 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewDetailResponse> insert(@RequestBody ReviewDetailRequest request) throws NotFoundException {
        return ApiResponse.ok(reviewService.insert(request));
    }

    // 칵테일 id를 통한 조회
    @GetMapping("/review/{userId}")
    public ApiResponse<List<ReviewDetailResponse>> findAllByUserId(Pageable pageable, @PathVariable("userId") Long userId) throws NotFoundException {
        return ApiResponse.ok(reviewService.findAllByUserId(pageable, userId));
    }

    // 사용자 id를 통한 조회
    @GetMapping("/review/{cocktailId}")
    public ApiResponse<List<ReviewDetailResponse>> findAllByCocktailId(Pageable pageable, @PathVariable("cocktailId") Long cocktailId) throws NotFoundException {
        return ApiResponse.ok(reviewService.findAllByCocktailId(pageable, cocktailId));
    }

    @DeleteMapping("/review/{id}") // 리뷰 삭제
    public ApiResponse<String> delete(@PathVariable("id") Long reviewId, Users user) throws Exception {
        reviewService.delete(reviewId, user);
        return ApiResponse.ok(user.getEmail() + "님의 리뷰가 삭제되었습니다.");
    }

    @DeleteMapping("admin/review/{id}") // 관리자의 리뷰 삭제
    public ApiResponse<Void> deleteByAdmin(@PathVariable("id") Long reviewId) throws Exception {
        reviewService.deleteByAdmin(reviewId);
        return ApiResponse.ok(null);
    }

    @PutMapping("/review/{id}") // 리뷰 수정
    public ApiResponse<Void> updateByReviewId(@PathVariable Long id, @RequestBody ReviewUpdateRequest request) throws Exception {
        return ApiResponse.ok(null);
    }
}
