package com.shake_match.alchomist.users.dto.response;

import com.shake_match.alchomist.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Getter
public class UserResponse {

    private String name;
    private String nickname;
    private boolean gender;
    private int age;
    private String mbti;

    public UserResponse(Users savedUser) {
        this.name = savedUser.getName();
        this.nickname = savedUser.getNickname();
        this.gender = savedUser.isGender();
        this.age = savedUser.getAge();
        this.mbti = savedUser.getMbti();
    }

}
