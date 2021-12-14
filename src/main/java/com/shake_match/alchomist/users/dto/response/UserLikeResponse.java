package com.shake_match.alchomist.users.dto.response;

import com.shake_match.alchomist.users.Users;
import lombok.Getter;

@Getter
public class UserLikeResponse {

    Users user;

    public UserLikeResponse(Users users) {
        this.user = users;
    }

}
