package com.shake_match.alchomist.users.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shake_match.alchomist.ingredient.dto.response.IngredientResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserDetailResponse {

    private Long id;
    private String nickname;
    private Boolean isMan;
    private int age;
    private String mbti;
    private List<UserBookmarkResponse> userBookmarkResponses;

    @JsonProperty("ingredients")
    private List<IngredientResponse> ingredients;

    public UserDetailResponse(Long id, String nickname, Boolean isMan, int age, String mbti, List<IngredientResponse> ingredients) {
        this.id = id;
        this.nickname = nickname;
        this.isMan = isMan;
        this.age = age;
        this.mbti = mbti;
        if (ingredients == null){
            this.ingredients = new ArrayList<>();
        }
        else {
            this.ingredients = ingredients;
        }
    }


    public UserDetailResponse(Long id, String nickname, Boolean isMan, int age, String mbti, List<IngredientResponse> ingredients, List<UserBookmarkResponse> bookmark) {
        this.id = id;
        this.nickname = nickname;
        this.isMan = isMan;
        this.age = age;
        this.mbti = mbti;
        if (ingredients == null){
            this.ingredients = new ArrayList<>();
        }
        else {
            this.ingredients = ingredients;
        }
        this.bookmark = bookmark;
    }
}
