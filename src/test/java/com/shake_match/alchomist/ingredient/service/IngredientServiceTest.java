package com.shake_match.alchomist.ingredient.service;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.ingredient.dto.request.IngredientDetailRequest;
import com.shake_match.alchomist.ingredient.dto.response.IngredientDetailResponse;
import com.shake_match.alchomist.ingredient.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class IngredientServiceTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private CocktailRepository cocktailRepository;

    private final Long INGREDIENT_ID = 1L;

    private final String INGREDIENT_NAME = "레몬주스";

    @BeforeEach
    public void saveTest() {

        Cocktail cocktailTest1 = Cocktail.builder()
                .name("모히또")
                .recipe("화이트럼 45ml, 민트 6, 설탕 2tsp, 라임 20ml, 탄산수")
                .likes(0)
                .type("이미지 url")
                .totalRating(4.3f)
                .youtubeLink("https://www.youtube.com/watch?v=8getArw9Ryk")
                .build();
        cocktailRepository.save(cocktailTest1);

        Cocktail cocktailTest2 = Cocktail.builder()
                .name("모히또가서 몰디브 한잔")
                .recipe("화이트럼 45ml, 민트 6, 설탕 2tsp, 라임 20ml, 탄산수")
                .likes(0)
                .type("이미지 url")
                .totalRating(4.3f)
                .youtubeLink("https://www.youtube.com/watch?v=8getArw9Ryk")
                .build();
        cocktailRepository.save(cocktailTest2);

        List<Cocktail> cocktails = new ArrayList<>();
        cocktails.add(cocktailTest1);
        cocktails.add(cocktailTest2);

        IngredientDetailRequest request = IngredientDetailRequest.builder()
                //.ingredientId(INGREDIENT_ID)
                .ingredientName("레몬주스")
                .cocktails(cocktails)
                .build();

        ingredientService.save(request);
    }

    @AfterEach
    public void cleanup() {
        ingredientRepository.deleteAll();
    }

    @Test
    @DisplayName("재료 전체 조회 테스트")
    public void insertTest() {
        List<Ingredient> ingredientList = ingredientRepository.findAll();
        assertThat(ingredientList.size()).isEqualTo(1); // 재료 1개
    }

    @Test
    @DisplayName("재료 id 조회 테스트")
    public void findOneByIdTest() {
        IngredientDetailResponse responses = ingredientService.findById(INGREDIENT_ID);
        assertThat(responses.getIngredientId()).isEqualTo(INGREDIENT_ID);
    }

    @Test
    @DisplayName("재료 name 조회 테스트")
    public void findOneByNameTest() {
        IngredientDetailResponse responses = ingredientService.findByName(INGREDIENT_NAME);
        assertThat(responses.getIngredientName()).isEqualTo(INGREDIENT_NAME);
    }
}
