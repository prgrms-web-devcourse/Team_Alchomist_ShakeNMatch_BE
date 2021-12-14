package com.shake_match.alchomist.users.dto.response;

import com.shake_match.alchomist.ingredient.dto.response.IngredientResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
public class UserDetailResponse {

    private String name;
    private String nickname;
    private boolean isMan;
    private int age;
    private String mbti;
    private String imageUrl;

    List<IngredientResponse> ingredients = new ArrayList<>();

    public UserDetailResponse(String name, String nickname, boolean isMan, int age, String mbti,
                              String imageUrl,
                              List<IngredientResponse> ingredients) {
        this.name = name;
        this.nickname = nickname;
        this.isMan = isMan;
        this.age = age;
        this.mbti = mbti;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

}
