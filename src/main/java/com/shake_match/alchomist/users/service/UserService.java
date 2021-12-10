package com.shake_match.alchomist.users.service;

import com.shake_match.alchomist.cocktail.domain.Cocktail;
import com.shake_match.alchomist.cocktail.repository.CocktailRepository;
import com.shake_match.alchomist.global.ErrorCode;
import com.shake_match.alchomist.global.NotFoundException;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.converter.UserConverter;
import com.shake_match.alchomist.users.dto.request.UserRequest;
import com.shake_match.alchomist.users.dto.response.UserBookmarkResponse;
import com.shake_match.alchomist.users.dto.response.UserIngredientsResponse;
import com.shake_match.alchomist.users.dto.response.UserLikeResponse;
import com.shake_match.alchomist.users.dto.response.UserResponse;
import com.shake_match.alchomist.users.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final CocktailRepository cocktailRepository;

    private final UserConverter userConverter;


    @Transactional
    public UserResponse addUser(UserRequest userRequest) {
        Users savedUser = userRepository.save(userConverter.convertUser(userRequest));
        return new UserResponse(savedUser);

    }

    @Transactional
    public Users getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
    }


    @Transactional
    public Users getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_NICKNAME));
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
    public UserLikeResponse deleteBookmark(Long userId, Long cocktailId) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.NOT_EXIST_MEMBER));
        Cocktail cocktail = cocktailRepository.findById(cocktailId)
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
