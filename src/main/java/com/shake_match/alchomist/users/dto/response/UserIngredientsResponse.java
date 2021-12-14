package com.shake_match.alchomist.users.dto.response;

import lombok.Getter;

@Getter
public class UserIngredientsResponse {

    private Long id;
    private String name;
    private String type;
    private boolean isAlcohol;
    private String measure;

    public UserIngredientsResponse(Long id, String name, String type, boolean isAlcohol,
                                   String measure) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isAlcohol = isAlcohol;
        this.measure = measure;
    }
}
