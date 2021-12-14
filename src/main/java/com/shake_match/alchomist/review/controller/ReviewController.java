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
import com.shake_match.alchomist.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final S3Service s3Service;

    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository, S3Service s3Service) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
        this.s3Service = s3Service;
    }

    @PostMapping("/review") // 리뷰 생성
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewDetailResponse> insert(@RequestBody ReviewDetailRequest request) throws NotFoundException {
        return ApiResponse.ok(reviewService.insert(request));
    }

    // 사용자 id를 통한 조회
    @GetMapping("/review/{id}")
    public ApiResponse<List<ReviewDetailResponse>> findAllByUserId(@PathVariable("id") Long userId, Pageable pageable) throws NotFoundException {
        return ApiResponse.ok(reviewService.findAllByUserId(pageable, userId));
    }

    // 칵테일 id를 통한 조회
    @GetMapping("/review/cocktailId")
    public ApiResponse<List<ReviewDetailResponse>> findAllByCocktailId(@RequestParam Long cocktailId, Pageable pageable) throws NotFoundException {
        return ApiResponse.ok(reviewService.findAllByCocktailId(pageable, cocktailId));
    }

    @DeleteMapping("/review/{id}") // 리뷰 삭제
    public ApiResponse<String> delete(@PathVariable("id") Long id) throws Exception {
        reviewService.delete(id);
        return ApiResponse.ok("리뷰가 삭제되었습니다.");
    }

    @PutMapping("/review/{id}") // 리뷰 수정
    public ApiResponse<ReviewUpdateResponse> updateByReviewId(@PathVariable("id") Long id, @RequestBody ReviewUpdateRequest request) throws Exception {
        return ApiResponse.ok(reviewService.updateById(id, request));
    }

    @GetMapping("/review")
    public ApiResponse<List<ReviewDetailResponse>> findAll(Pageable pageable){
        return ApiResponse.ok(
                reviewService.findAll(pageable)
                .stream()
                .collect(Collectors.toList()));
    }

//    @GetMapping("/review/image")
//    public ModelAndView getImage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("test"); // html 이름 지정
//        modelAndView.addObject("response", "이미지 URL"); // 넘겨줄 값 지정
//        return modelAndView;
//    }
//
//    @PostMapping("/review/image")
//    public ApiResponse<String> saveImage(@RequestBody ReviewImageRequest request, MultipartFile file) throws IOException {
//        String imgPath = s3Service.upload(file.getOriginalFilename(), file);
//        request.setImageUrl(imgPath);
//        reviewService.saveImage(request);
//        return ApiResponse.ok("redirect:/test");
//    }
}
