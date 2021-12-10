package com.shake_match.alchomist.users.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {

    private String name;

    private String nickname;

    private boolean gender;

    private int age;

    private String mbti;

}
