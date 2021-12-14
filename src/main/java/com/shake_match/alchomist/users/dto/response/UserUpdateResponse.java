package com.shake_match.alchomist.users.dto.response;

import lombok.Getter;

@Getter
public class UserUpdateResponse {

    private String nickname;
    private boolean isMan;
    private int age;
    private String mbti;

    public UserUpdateResponse(String nickname, boolean isMan, int age, String mbti) {
        this.nickname = nickname;
        this.isMan = isMan;
        this.age = age;
        this.mbti = mbti;
    }
}
