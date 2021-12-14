package com.shake_match.alchomist.review.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDto {

    private int rating;
    private String description;
    private String url;
    private Long userId;
    private String userName;
    private Long cocktailId;
    private String cocktailName;


    public ReviewDto(int rating,
                     String description,
                     String url,
                     Long userId,
                     String userName,
                     Long cocktailId,
                     String cocktailName) {
        this.rating = rating;
        this.description = description;
        this.url = url;
        this.userId = userId;
        this.userName = userName;
        this.cocktailId = cocktailId;
        this.cocktailName = cocktailName;
    }
}