package com.shake_match.alchomist.users.dto.request;

import lombok.Getter;

@Getter
public class UserRequest {

    private String nickname;

    private Boolean isMan;

    private int age;

    private String mbti;

    public UserRequest(String nickname, boolean isMan, int age, String mbti) {
        this.nickname = nickname;
        this.isMan = isMan;
        this.age = age;
        this.mbti = mbti;
    }
}
