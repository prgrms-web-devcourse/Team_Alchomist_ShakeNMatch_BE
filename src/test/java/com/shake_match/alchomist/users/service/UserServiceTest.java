package com.shake_match.alchomist.users.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.shake_match.alchomist.users.converter.UserConverter;
import com.shake_match.alchomist.users.dto.request.UserRequest;
import com.shake_match.alchomist.users.dto.response.UserDetailResponse;
import com.shake_match.alchomist.users.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserServiceTest {

    UserRequest userRequest = new UserRequest("나상원", "rudolf", "www.url.com", true, 28, "ENFJ");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;


    @Test
    @DisplayName("유저 생성 테스트")
    public void addUserTest() {
        UserDetailResponse userResponse = userService.addUser(userRequest);
        assertThat(userResponse.getNickname()).isEqualTo("rudolf");
    }

}
