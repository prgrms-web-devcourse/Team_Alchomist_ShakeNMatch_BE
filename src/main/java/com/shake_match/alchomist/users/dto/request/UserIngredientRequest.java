package com.shake_match.alchomist.users.dto.request;

import lombok.Getter;

@Getter
public class UserIngredientRequest {

    private final Long userid;
    private final Long ingredientid;

    public UserIngredientRequest(Long userid, Long ingredientid) {
        this.userid = userid;
        this.ingredientid = ingredientid;
    }
}
