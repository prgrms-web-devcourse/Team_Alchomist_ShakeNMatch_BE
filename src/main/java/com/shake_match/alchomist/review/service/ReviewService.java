package com.shake_match.alchomist.review.service;

import com.shake_match.alchomist.amazon.service.S3Service;
import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.global.ErrorCode;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.converter.ReviewConverter;
import com.shake_match.alchomist.review.dto.request.ReviewDetailRequest;
import com.shake_match.alchomist.review.dto.request.ReviewUpdateRequest;
import com.shake_match.alchomist.review.dto.response.ReviewDetailResponse;
import com.shake_match.alchomist.review.dto.response.ReviewUpdateResponse;
import com.shake_match.alchomist.review.repository.ReviewRepository;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
    private final UserRepository userRepository;
    private final CocktailRepository cocktailRepository;
    private final S3Service s3Service;

    public ReviewService(ReviewRepository reviewRepository, ReviewConverter reviewConverter, UserRepository userRepository, CocktailRepository cocktailRepository, S3Service s3Service) {
        this.reviewRepository = reviewRepository;
        this.reviewConverter = reviewConverter;
        this.userRepository = userRepository;
        this.cocktailRepository = cocktailRepository;
        this.s3Service = s3Service;
    }


    @Transactional // 리뷰 작성
    public ReviewDetailResponse insert(ReviewDetailRequest request) throws NotFoundException {
        getUser(request.getUserId());
        getCocktail(request.getCocktailId());
        Review review = reviewConverter.converterReviewDetail(request, getUser(request.getUserId()).getNickname(), getCocktail(request.getCocktailId()).getName());
        reviewRepository.save(review);
        return new ReviewDetailResponse(review);
    }

    @Transactional // 리뷰 삭제
    public void delete(Long reviewId) throws IOException {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_REVIEW));
        s3Service.delete(review.getUrl());
        reviewRepository.delete(review);
    }

    @Transactional // ingredient 수정
    public ReviewUpdateResponse updateById(Long reviewId, ReviewUpdateRequest request) throws NotFoundException {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_INGREDIENT));
        review.update(request);
        return new ReviewUpdateResponse(review);
    }

    @Transactional // 리뷰 전체 조회
    public Page<ReviewDetailResponse> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable)
                .map(ReviewDetailResponse::new);
    }

    @Transactional // 리뷰, 사용자 id를 이용한 조회
    public List<ReviewDetailResponse> findAllByUserId(Pageable pageable, Long userId) throws NotFoundException {
        return reviewRepository.findAll(pageable)
                .map(ReviewDetailResponse::new)
                .stream().filter(x -> x.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Transactional // 리뷰, 칵테일 id를 이용한 조회
    public List<ReviewDetailResponse> findAllByCocktailId(Pageable pageable, Long cocktailId) throws NotFoundException {
        return reviewRepository.findAll(pageable)
                .map(ReviewDetailResponse::new)
                .stream().filter(x -> x.getCocktailId().equals(cocktailId))
                .collect(Collectors.toList());
    }

    @Transactional // 실제로 저장되어있는 사용자인지 확인하고 조회하는 메소드
    public Users getUser(Long userId) throws NotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
    }

    @Transactional // 실제로 저장되어있는 칵테일인지 확인하고 조회하는 메소드
    public Cocktail getCocktail(Long cocktailId) throws NotFoundException {
        return cocktailRepository.findById(cocktailId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_COCKTAIL));
    }
}
