package com.shake_match.alchomist.review.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewDto {

    private Long id;
    private int rating;
    private String description;
    private String url;
    private Long userId;
    private String nickname;
    private Long cocktailId;
    private String cocktailName;

    public ReviewDto(Long id, int rating, String description, String url, Long userId, String userName, Long cocktailId, String cocktailName) {
        this.id = id;
        this.rating = rating;
        this.description = description;
        this.url = url;
        this.userId = userId;
        this.nickname = userName;
        this.cocktailName = cocktailName;
        this.cocktailId = cocktailId;
    }
}
