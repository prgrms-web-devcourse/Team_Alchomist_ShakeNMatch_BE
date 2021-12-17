package com.shake_match.alchomist.users.dto.request;

import lombok.Getter;

@Getter
public class UserJoinRequest {

    private String nickname;
    private Boolean isMan;
    private int age;
    private String mbti;
}
