package com.shake_match.alchomist.users.service;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.global.ErrorCode;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.ingredient.converter.IngredientConverter;
import com.shake_match.alchomist.ingredient.dto.response.IngredientListResponse;
import com.shake_match.alchomist.ingredient.dto.response.IngredientToListResponse;
import com.shake_match.alchomist.ingredient.repository.IngredientRepository;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.UsersIngredient;
import com.shake_match.alchomist.users.converter.UserConverter;
import com.shake_match.alchomist.users.dto.request.UserBookmarkRequest;
import com.shake_match.alchomist.users.dto.request.UserRequest;
import com.shake_match.alchomist.users.dto.request.UserUpdateRequest;
import com.shake_match.alchomist.users.dto.response.UserBookmarkResponse;
import com.shake_match.alchomist.users.dto.response.UserDetailResponse;
import com.shake_match.alchomist.users.dto.response.UserLikeResponse;
import com.shake_match.alchomist.users.dto.response.UserNicknameResponse;
import com.shake_match.alchomist.users.dto.response.UserUpdateResponse;
import com.shake_match.alchomist.users.repository.UserIngredientRepository;
import com.shake_match.alchomist.users.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final CocktailRepository cocktailRepository;

    private final IngredientRepository ingredientRepository;

    private final UserConverter userConverter;

    private final IngredientConverter ingredientConverter;

    private final UserIngredientRepository userIngredientRepository;

    @Transactional
    public UserDetailResponse addUser(UserRequest userRequest) {
        if (userRepository.findByNickname(userRequest.getNickname()).isPresent()) {
            throw new NotFoundException(ErrorCode.DUPLICATION_MEMBER_NICKNAME);
        }
        Users savedUser = userRepository.save(userConverter.toUser(userRequest));
        return userConverter.toUserResponse(savedUser);
    }

    @Transactional
    public UserUpdateResponse updateById(Long userId, UserUpdateRequest userUpdateRequest)
        throws Exception {
        Users changedUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        changedUser.update(userUpdateRequest);
        return userConverter.toUserUpdateResponse(changedUser);
    }

    @Transactional
    public UserDetailResponse getUserDetail(Long id) {
        Users user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        return userConverter.toUserResponse(user);
    }

    @Transactional
    public IngredientToListResponse getUserByIngredient(Long id) {
        Users user = userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        List<IngredientListResponse> ingredientListResponseList =
            user.getUsersIngredient().stream()
                .map(UsersIngredient::getIngredient)
                .map(ingredientConverter::converterIngredientListResponse)
                .collect(Collectors.toList());
        return ingredientConverter.converterIngredientToListResponse(ingredientListResponseList);
    }

    @Transactional
    public void saveIngredientOfUser(Long userId, List<Long> ingredientIds) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));

        List<UsersIngredient> usersIngredients = ingredientIds.stream()
            .map(ingredientId -> ingredientRepository.getById(ingredientId))
            .map(ingredient -> new UsersIngredient(user, ingredient))
            .collect(Collectors.toList());
        userIngredientRepository.saveAll(usersIngredients);
    }


    @Transactional
    public void deletedIngredientOfUser(Long userId, List<Long> ingredientIds) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        userIngredientRepository.deleteUsersIdAndIngredientIds(userId, ingredientIds);
    }


    @Transactional
    public Users getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
    }

    @Transactional
    public UserNicknameResponse getUserByNickname(String nickname) {
        boolean can = userRepository.findByNickname(nickname).isEmpty();
        return userConverter.toUserNicknameResponse(can);
    }

    @Transactional
    public UserLikeResponse addBookmark(Long userId, Long cocktailId) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        Cocktail cocktail = cocktailRepository.findById(cocktailId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_COCKTAIL));
        user.addCocktails(cocktail);
        return new UserLikeResponse(user);
    }

    @Transactional
    public UserLikeResponse deleteBookmark(UserBookmarkRequest userBookmarkRequest) {
        Users user = userRepository.findById(userBookmarkRequest.getUserId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        Cocktail cocktail = cocktailRepository.findById(userBookmarkRequest.getCocktailId())
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_COCKTAIL));
        user.addCocktails(cocktail);
        return new UserLikeResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserBookmarkResponse> getBookmarkById(Long userId) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        List<Cocktail> cocktailByBookmark = user.getCocktails();

        if (cocktailByBookmark.isEmpty()) {
            throw new NotFoundException(ErrorCode.NOT_EXIST_BOOKMARK);
        }

        List<UserBookmarkResponse> responses = new ArrayList<>();
        for (Cocktail cocktail : cocktailByBookmark) {
            responses.add(userConverter.toSearchByBookmark(cocktail));
        }
        return responses;
    }


}
