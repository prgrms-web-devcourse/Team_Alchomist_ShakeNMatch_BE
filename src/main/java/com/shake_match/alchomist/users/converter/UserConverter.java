package com.shake_match.alchomist.users.converter;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.ingredient.converter.IngredientConverter;
import com.shake_match.alchomist.ingredient.dto.response.IngredientResponse;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.UsersIngredient;
import com.shake_match.alchomist.users.dto.response.UserBookmarkResponse;
import com.shake_match.alchomist.users.dto.response.UserDetailResponse;
import com.shake_match.alchomist.users.dto.response.UserNicknameResponse;
import com.shake_match.alchomist.users.dto.response.UserUpdateResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final IngredientConverter ingredientConverter;

    public UserConverter(
        IngredientConverter ingredientConverter) {
        this.ingredientConverter = ingredientConverter;
    }

    public UserBookmarkResponse toSearchByBookmark(Cocktail cocktail) {
        return new UserBookmarkResponse(
            cocktail.getId(),
            cocktail.getName(),
            cocktail.getType()
        );
    }

    public UserDetailResponse toUserResponse(Users user) {
        return new UserDetailResponse(
            user.getId(),
            user.getNickname(),
            user.getIsMan(),
            user.getAge(),
            user.getMbti(),
            toIngredientsResponses(user.getUsersIngredient())
        );
    }

    public UserUpdateResponse toUserUpdateResponse(Users users) {
        return new UserUpdateResponse(
            users.getNickname(),
            users.getIsMan(),
            users.getAge(),
            users.getMbti()
        );
    }

    public UserNicknameResponse toUserNicknameResponse(boolean can) {
        return new UserNicknameResponse(can);
    }

    public List<IngredientResponse> toIngredientsResponses(List<UsersIngredient> usersIngredients) {
        List<IngredientResponse> users = new ArrayList<>();
        for (UsersIngredient usersIngredient : usersIngredients) {
            users.add(
                ingredientConverter.converterIngredientResponse(usersIngredient.getIngredient()));
        }
        return users;
    }

}
