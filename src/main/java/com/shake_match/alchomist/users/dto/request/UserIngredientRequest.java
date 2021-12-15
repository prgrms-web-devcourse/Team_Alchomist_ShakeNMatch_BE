package com.shake_match.alchomist.users.dto.request;

import lombok.Getter;

@Getter
public class UserIngredientRequest {

    private final String userid;
    private final String ingredientid;

    public UserIngredientRequest(String userid, String ingredientid) {
        this.userid = userid;
        this.ingredientid = ingredientid;
    }
}
