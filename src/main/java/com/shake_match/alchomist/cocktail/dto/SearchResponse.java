package com.shake_match.alchomist.cocktail.dto;

import lombok.Getter;

@Getter
public class SearchResponse {

    Long id;

    String name;

    String type;

    public SearchResponse(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
