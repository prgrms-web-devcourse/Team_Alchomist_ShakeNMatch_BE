package com.shake_match.alchomist.users.controller;

import com.shake_match.alchomist.global.ApiResponse;
import com.shake_match.alchomist.users.dto.request.UserBookmarkRequest;
import com.shake_match.alchomist.users.dto.request.UserRequest;
import com.shake_match.alchomist.users.dto.response.UserBookmarkResponse;
import com.shake_match.alchomist.users.dto.response.UserDetailResponse;
import com.shake_match.alchomist.users.dto.response.UserLikeResponse;
import com.shake_match.alchomist.users.dto.response.UserNicknameResponse;
import com.shake_match.alchomist.users.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join") // 회원가입
    public ApiResponse<UserDetailResponse> saveUser(@RequestBody @Valid UserRequest userRequest) {
        UserDetailResponse userResponse = userService.addUser(userRequest);
        return ApiResponse.ok(userResponse);
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

    @DeleteMapping("/bookmark/{bookmark_id}") // 북마크 삭제
    public ApiResponse<String> deleteUserBookmark(@PathVariable("bookmark_id") java.lang.Long userId,
                                                  java.lang.Long cocktailId) {
        UserLikeResponse userLikeResponse = userService.deleteBookmark(userId, cocktailId);
        return ApiResponse.ok("bookmark added successfully");
    }

    @DeleteMapping("/bookmark") // 북마크 삭제
    public ApiResponse<String> deleteUserBookmark(@RequestBody UserBookmarkRequest userBookmarkRequest) {
        userService.deleteBookmark(userBookmarkRequest);
        return ApiResponse.ok("bookmark Deleted successfully");
    }


}
