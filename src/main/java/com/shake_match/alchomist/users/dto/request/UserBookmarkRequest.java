package com.shake_match.alchomist.users.dto.request;

import lombok.Getter;

@Getter
public class UserBookmarkRequest {

    private Long userId;

    private Long cocktailId;

    public UserBookmarkRequest(Long userId, Long cocktailId) {
        this.userId = userId;
        this.cocktailId = cocktailId;
    }
}
