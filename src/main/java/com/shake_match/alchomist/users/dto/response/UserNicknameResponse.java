package com.shake_match.alchomist.users.dto.response;

import lombok.Getter;

@Getter
public class UserNicknameResponse {

    private boolean can;

    public UserNicknameResponse(boolean can) {
        this.can = can;
    }

}
