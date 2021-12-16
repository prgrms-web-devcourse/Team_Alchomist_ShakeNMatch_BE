package com.shake_match.alchomist.review.service;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.review.Review;
import com.shake_match.alchomist.review.dto.request.ReviewDetailRequest;
import com.shake_match.alchomist.review.dto.response.ReviewDetailResponse;
import com.shake_match.alchomist.review.repository.ReviewRepository;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CocktailRepository cocktailRepository;

    private final Long USER_ID = 1L;

    private final Long COCKTAIL_ID = 1L;

    private final Long REVIEW_ID1 = 1L;

    private final Long REVIEW_ID2 = 2L;

    private List<Review> reviews = new ArrayList<>();

    @Test
    @DisplayName("칵테일이 리뷰를 가지고 있는지, 리뷰 저장이 잘 되는지 테스트")
    public void saveTest() {
        Review review1 = Review.builder()
                .id(REVIEW_ID1)
                .description("테스트용 묘사")
                .rating(5)
                .url("이미지 url")
                .build();
        reviewRepository.save(review1);

        Review review2 = Review.builder()
                .id(REVIEW_ID2)
                .description("테스트용 묘사")
                .rating(1)
                .url("이미지 url")
                .build();
        reviewRepository.save(review2);

        reviews.add(review1);
        reviews.add(review2);

        Cocktail cocktail = Cocktail.builder()
                .id(COCKTAIL_ID)
                .name("모히또가서 몰디브 한잔")
                .recipe("화이트럼 45ml, 민트 6, 설탕 2tsp, 라임 20ml, 탄산수")
                .likes(0)
                .type("이미지 url")
                .totalRating(4.3f)
                .reviews(reviews)
                .build();
        cocktailRepository.save(cocktail);
        assertThat(cocktail.getReviews().contains(review1)).isEqualTo(true);
    }

    @AfterEach
    public void cleanup() {
        cocktailRepository.deleteAll();
        reviewRepository.deleteAll();
    }
}
