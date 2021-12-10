package com.shake_match.alchomist.users.converter;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.dto.request.UserRequest;
import com.shake_match.alchomist.users.dto.response.UserBookmarkResponse;
import org.springframework.stereotype.Controller;

@Controller
public class UserConverter {

    public UserBookmarkResponse toSearchByBookmark(Cocktail cocktail) {
        return new UserBookmarkResponse(
            cocktail.getId(),
            cocktail.getName(),
            cocktail.getImageUrl()
        );
    }

    // DTO -> entity
    public Users convertUser(UserRequest userRequest) {
        return Users.builder()
            .nickname(userRequest.getNickname())
            .gender(userRequest.isGender())
            .age(userRequest.getAge())
            .mbti(userRequest.getMbti())
            .build();
    }

    // entity -> DTO
    public UserRequest convertUserRequest(Users users) {
        return UserRequest.builder()
            .nickname(users.getNickname())
            .gender(users.isGender())
            .age(users.getAge())
            .mbti(users.getMbti())
            .build();
    }

}
