package com.shake_match.alchomist.oauth2;

import com.shake_match.alchomist.jwt.Jwt;
import com.shake_match.alchomist.users.Users;
import com.shake_match.alchomist.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final Jwt jwt;

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws ServletException, IOException {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            OAuth2User principal = oauth2Token.getPrincipal();
            String registrationId = oauth2Token.getAuthorizedClientRegistrationId();

            Users user = processUserOAuth2UserJoin(principal, registrationId);
            String loginSuccessJson = generateLoginSuccessJson(user);
             String redirectUrl = "https://shakenmatch.vercel.app/oauth/kakao" + loginSuccessJson;
//            String redirectUrl = "http://localhost:3000/oauth/kakao" + loginSuccessJson;
            getRedirectStrategy().sendRedirect(request, response, redirectUrl);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    private Users processUserOAuth2UserJoin(OAuth2User oAuth2User, String registrationId) {
        return userService.join(oAuth2User, registrationId);
    }

    private String generateLoginSuccessJson(Users user) {
        String token = generateToken(user);
        log.debug("Jwt({}) created for oauth2 login user {}", token, user.getUsername());
        if(user.getNickname().equals("")) {
            return "?token=" + token + "&needInfo=true";
        }
        return "?token=" + token + "&needInfo=false";
    }

    private String generateToken(Users user) {
        return jwt.sign(Jwt.Claims.from(user.getUsername(), new String[]{"ROLE_USER"}));
    }

}
