package com.shake_match.alchomist.users.dto.request;

import lombok.Getter;

@Getter
public class UserRequest {

    private String name;

    private String nickname;

    private String imageUrl;

    private boolean isMan;

    private int age;

    private String mbti;

    public UserRequest(String name, String nickname, String imageUrl, boolean isMan, int age,
                       String mbti) {
        this.name = name;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.isMan = isMan;
        this.age = age;
        this.mbti = mbti;
    }
}
