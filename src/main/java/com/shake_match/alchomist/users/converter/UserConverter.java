package com.shake_match.alchomist.users.converter;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.ingredient.Ingredient;
import com.shake_match.alchomist.ingredient.converter.IngredientConverter;
import com.shake_match.alchomist.ingredient.dto.response.IngredientResponse;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.dto.request.UserRequest;
import com.shake_match.alchomist.users.dto.response.UserBookmarkResponse;
import com.shake_match.alchomist.users.dto.response.UserDetailResponse;
import com.shake_match.alchomist.users.dto.response.UserNicknameResponse;
import com.shake_match.alchomist.users.dto.response.UserUpdateResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;

@Controller
public class UserConverter {

    private IngredientConverter ingredientConverter;

    public UserConverter(
        IngredientConverter ingredientConverter) {
        this.ingredientConverter = ingredientConverter;
    }

    public UserBookmarkResponse toSearchByBookmark(Cocktail cocktail) {
        return new UserBookmarkResponse(
            cocktail.getId(),
            cocktail.getName(),
            cocktail.getImageUrl()
        );
    }

    public UserDetailResponse toUserResponse(Users user) {
        return new UserDetailResponse(
            user.getId(),
            user.getEmail(),
            user.getNickname(),
            user.isMan(),
            user.getAge(),
            user.getMbti(),
            user.getImageUrl(),
            toIngredientsResponses(user.getIngredients())
        );
    }

    public Users toUser(UserRequest userRequest) {
        return new Users(
            userRequest.getEmail(),
            userRequest.getNickname(),
            userRequest.getImageUrl(),
            userRequest.isMan(),
            userRequest.getAge(),
            userRequest.getMbti()
        );
    }

    public UserUpdateResponse toUserUpdateResponse(Users users) {
        return new UserUpdateResponse(
            users.getNickname(),
            users.isMan(),
            users.getAge(),
            users.getMbti()
        );
    }

    public UserNicknameResponse toUserNicknameResponse(boolean can) {
        return new UserNicknameResponse(can);
    }

    public List<IngredientResponse> toIngredientsResponses(List<Ingredient> ingredients) {
        return ingredients.stream()
            .map(ingredient -> ingredientConverter.converterIngredientResponse(
                ingredient)).collect(Collectors.toList());
    }

}
