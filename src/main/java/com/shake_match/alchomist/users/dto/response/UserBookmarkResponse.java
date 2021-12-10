package com.shake_match.alchomist.users.dto.response;

public class UserBookmarkResponse {

    Long id;

    String name;

    String imageUrl;

    public UserBookmarkResponse(Long id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

}
