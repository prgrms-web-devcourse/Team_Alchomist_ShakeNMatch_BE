package com.shake_match.alchomist.users.controller;

import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.jwt.JwtAuthentication;
import com.shake_match.alchomist.users.converter.UserConverter;
import com.shake_match.alchomist.users.dto.response.UserDetailResponse;
import com.shake_match.alchomist.ingredient.dto.response.IngredientToListResponse;
import com.shake_match.alchomist.users.dto.request.UserBookmarkRequest;
import com.shake_match.alchomist.users.dto.request.UserRequest;
import com.shake_match.alchomist.users.dto.request.UserUpdateRequest;
import com.shake_match.alchomist.users.dto.response.UserBookmarkResponse;
import com.shake_match.alchomist.users.dto.response.UserLikeResponse;
import com.shake_match.alchomist.users.dto.response.UserNicknameResponse;
import com.shake_match.alchomist.users.service.UserService;
import java.util.List;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final UserConverter converter;

    public UserController(UserService userService, UserConverter converter) {
        this.userService = userService;
        this.converter = converter;
    }

    @PutMapping("/info/{userId}") // 회원정보 수정
    public ApiResponse<String> updateUserInfo(@PathVariable("userId") Long id,
                                              @RequestBody UserUpdateRequest userUpdateRequest)
        throws Exception {
        userService.updateById(id, userUpdateRequest);
        return ApiResponse.ok("회원정보가 수정되었습니다");
    }

    @GetMapping("/{userId}") // 유저 디테일 조회
    public ApiResponse<UserDetailResponse> findUserDetail(@PathVariable("userId") Long id) {
        UserDetailResponse userResponse = userService.getUserDetail(id);
        return ApiResponse.ok(userResponse);
    }

    @GetMapping("/nickname/{nickname}") // 닉네임 검색
    public ApiResponse<UserNicknameResponse> findUserByNickname(
        @PathVariable("nickname") String nickname) {
        UserNicknameResponse userNicknameResponse = userService.getUserByNickname(nickname);
        return ApiResponse.ok(userNicknameResponse);
    }

    @PostMapping("/bookmark") // 북마크 추가
    public ApiResponse<UserLikeResponse> addUserBookmark(
        @RequestBody UserBookmarkRequest userBookmarkRequest) {
        UserLikeResponse userLikeResponse = userService.addBookmark(userBookmarkRequest.getUserId(),
            userBookmarkRequest.getCocktailId());
        return ApiResponse.ok(userLikeResponse);
    }

    @GetMapping("/bookmark/{userId}") // 북마크 검색
    public ApiResponse<List<UserBookmarkResponse>> findBookmarkById(
        @PathVariable("userId") Long id) {
        List<UserBookmarkResponse> userBookmarkResponses = userService.getBookmarkById(id);
        return ApiResponse.ok(userBookmarkResponses);
    }

    @DeleteMapping("/bookmark") // 북마크 삭제
    public ApiResponse<String> deleteUserBookmark(
        @RequestBody UserBookmarkRequest userBookmarkRequest) {
        userService.deleteBookmark(userBookmarkRequest);
        return ApiResponse.ok("bookmark Deleted successfully");
    }
    // 내 술장고 재료조회
    @GetMapping("/ingredient/{userId}")
    public ApiResponse<IngredientToListResponse> findUserByIngredient(
        @PathVariable("userId") Long id) {
        IngredientToListResponse userByIngredient = userService.getUserByIngredient(id);
        return ApiResponse.ok(userByIngredient);
    }

    @PostMapping("/ingredient/{userId}")
    public ApiResponse<IngredientToListResponse> saveIngredientOfUser(
        @PathVariable("userId") Long id, @RequestBody List<Long> ingredientIds) {
        userService.saveIngredientOfUser(id, ingredientIds);
        IngredientToListResponse userByIngredient = userService.getUserByIngredient(id);
        return ApiResponse.ok(userByIngredient);
    }

    @DeleteMapping("/ingredient/{userId}")
    public ApiResponse<String> deleteUserIngredient(@PathVariable("userId") Long id,
                                                    @RequestBody List<Long> ingredientIds) {
        userService.deletedIngredientOfUser(id, ingredientIds);
        return ApiResponse.ok("술장고에 재료정보를 삭제하였습니다.");

    }
}
