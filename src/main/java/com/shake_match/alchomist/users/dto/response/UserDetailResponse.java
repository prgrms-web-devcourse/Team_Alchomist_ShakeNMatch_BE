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

    private Long id;
    private String email;
    private String nickname;
    private boolean isMan;
    private int age;
    private String mbti;
    private String imageUrl;

    List<IngredientResponse> ingredients = new ArrayList<>();

    public UserDetailResponse(Long id, String email, String nickname, boolean isMan, int age, String mbti,
                              String imageUrl,
                              List<IngredientResponse> ingredients) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.isMan = isMan;
        this.age = age;
        this.mbti = mbti;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
    }

}
