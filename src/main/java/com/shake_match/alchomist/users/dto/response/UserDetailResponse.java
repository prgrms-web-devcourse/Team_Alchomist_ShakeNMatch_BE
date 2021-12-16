package com.shake_match.alchomist.users.dto.response;

import com.shake_match.alchomist.ingredient.dto.response.IngredientResponse;
import lombok.Getter;
import java.util.List;

@Getter
public class UserDetailResponse {

    private Long id;
    private String nickname;
    private boolean isMan;
    private int age;
    private String mbti;
    private List<IngredientResponse> ingredients;

    public UserDetailResponse(String id, String nickname, boolean isMan, int age, String mbti, List<IngredientResponse> ingredients) {
        this.id = Long.parseLong(id);
        this.nickname = nickname;
        this.isMan = isMan;
        this.age = age;
        this.mbti = mbti;
        this.ingredients = ingredients;
    }
}
