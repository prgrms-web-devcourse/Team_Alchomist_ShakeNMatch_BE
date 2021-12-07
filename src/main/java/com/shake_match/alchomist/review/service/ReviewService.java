package com.shake_match.alchomist.review.service;

import com.shake_match.alchomist.cocktail.Cocktail;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.converter.ReviewConverter;
import com.shake_match.alchomist.review.dto.ReviewRequest;
import com.shake_match.alchomist.review.dto.ReviewResponse;
import com.shake_match.alchomist.review.repository.ReviewRepository;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.repository.UsersRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewConverter reviewConverter;
    private final UsersRepository usersRepository;

    public ReviewService(ReviewRepository reviewRepository, ReviewConverter reviewConverter, UsersRepository usersRepository) {
        this.reviewRepository = reviewRepository;
        this.reviewConverter = reviewConverter;
        this.usersRepository = usersRepository;
    }


    @Transactional // 리뷰 작성
    public ReviewResponse insert(ReviewRequest request, Users users, Cocktail cocktail) throws NotFoundException {
        Users user = getUser(users.getId());
        Review review = reviewConverter.converterReview(request, user, cocktail);
        review.setUsers(users);
        Review insertedReview = reviewRepository.save(review);
        return new ReviewResponse(insertedReview);
    }

    @Transactional // 리뷰 삭제
    public void delete(Long reviewId, Users users) throws Exception {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("해당 리뷰는 존재하지 않습니다."));
        if (!users.getId().equals(review.getUsers().getId())) { // 자기 자신의 리뷰만을 삭제 가능
            throw new Exception("본인이 작성한 리뷰가 아닙니다");
        }
        reviewRepository.delete(review);
    }

//    @Transactional // 리뷰, 칵테일 id를 이용한 조회
//    public Page<ReviewResponse> findAll(Pageable pageable) {
//        return reviewRepository.findAll(pageable)
//                .map(ReviewResponse::new);
//    }
//
//    @Transactional // 리뷰, 사용자 id를 이용한 조회
//    public ReviewResponse findById(Long reviewId) throws NotFoundException {
//        return reviewRepository.findById(reviewId)
//                .map(ReviewResponse::new)
//                .orElseThrow(() -> new NotFoundException("해당 리뷰는 존재하지 않습니다."));
//    }

    @Transactional // 실제로 저장되어있는 사용자인지 확인하고 조회하는 메소드
    public Users getUser(Long userId) throws NotFoundException {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("원하는 사용자를 찾을 수 없습니다"));
    }
}
