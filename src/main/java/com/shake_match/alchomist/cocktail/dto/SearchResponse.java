package com.shake_match.alchomist.cocktail.dto;

public class SearchResponse {

    Long id;

    String name;

    String imageUrl;

    public SearchResponse(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }
}
