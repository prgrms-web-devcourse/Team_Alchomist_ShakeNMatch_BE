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
                .type("이미지 url")
                .build();
        reviewRepository.save(review1);

        Review review2 = Review.builder()
                .id(REVIEW_ID2)
                .description("테스트용 묘사")
                .rating(1)
                .type("이미지 url")
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
                .youtubeLink("https://www.youtube.com/watch?v=8getArw9Ryk")
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

//    @Test
//    @DisplayName("사용자 id로 리뷰 조회 테스트")
//    public void findOneByUserIdTest() {
//        Users user = Users.builder()
//                .id(USER_ID)
//                .password("1234!")
//                .age(28)
//                .name("오재욱")
//                .nickname("우코")
//                .isMan(true)
//                .mbti("ENTJ")
//                .build();
//        userRepository.save(user);
//
//        Cocktail cocktail = Cocktail.builder()
//                .id(COCKTAIL_ID)
//                .name("모히또가서 몰디브 한잔")
//                .recipe("화이트럼 45ml, 민트 6, 설탕 2tsp, 라임 20ml, 탄산수")
//                .likes(0)
//                .type("이미지 url")
//                .totalRating(4.3f)
//                .youtubeLink("https://www.youtube.com/watch?v=8getArw9Ryk")
//                .build();
//        cocktailRepository.save(cocktail);
//
//        Review review = Review.builder()
//                .id(REVIEW_ID1)
//                .description("테스트용 묘사")
//                .rating(1)
//                .users(user)
//                .type("이미지")
//                .cocktails(cocktail)
//                .build();
//        reviewRepository.save(review);
//        PageRequest pageRequest = PageRequest.of(0, 10);
//        List<ReviewDetailResponse> responses = reviewService.findAllByUserId(pageRequest, USER_ID);
//        assertThat(responses.get(0).getUserId()).isEqualTo(USER_ID);
//    }

//    @Test
//    @DisplayName("칵테일 id로 리뷰 조회 테스트")
//    public void findOneByCocktailIdTest() {
//
//    }
//
//    @Test
//    @DisplayName("리뷰 삭제 테스트")
//    public void deleteTest() {
//        reviewService.delete();
//    }

}