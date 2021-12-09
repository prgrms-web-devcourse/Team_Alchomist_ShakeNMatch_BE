package com.shake_match.alchomist.global;

import java.util.Arrays;

public enum ErrorCode {
    INVALID_MEMBER_NAME("이름 형식이 맞지 않습니다."),
    INVALID_MEMBER_EMAIL("이메일 형식이 맞지 않습니다."),
    INVALID_MEMBER_AGE("나이 형식이 맞지 않습니다."),
    INVALID_MEMBER_HOBBY("취미 형식이 맞지 않습니다."),
    NOT_EXIST_MEMBER("존재하지 않는 회원입니다."),
    INTERNAL_SERVER_ERROR("정의되지 않은 서버 에러"),
    DUPLICATION_MEMBER_EMAIL("중복된 이메일입니다."),
    INVALID_POST_TITLE("제목 형식이 맞지 않습니다."),
    NOT_EXIST_POST("존재하지 않는 게시글 입니다."),
    INVALID_POST_STATUS("존재하지 않는 게시글 상태 입니다."),
    INVALID_IMAGE_URL("이미지의 URL 형식이 맞지 않습니다."),
    INVALID_IMAGE_TYPE("이미지의 종류 형식이 맞지 않습니다."),
    NOT_EXIST_COCKTAIL("존재하지 않는 칵테일입니다"),
    NOT_EXIST_IMAGE("존재하지 않는 이미지입니다"),
    NOT_EXIST_INGREDIENT("존재하지 않는 재료입니다"),
    NOT_EXIST_BADGE("존재하지 않는 배지입니다"),
    UNSUPPORTED_MEDIA_TYPE("지원하지 않는 미디어 타입입니다."),
    NOT_EXIST_LIKE("존재하지 않는 좋아요 입니다."),
    FORBIDDEN("권한이 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public static ErrorCode of(String errorMessage) {
        return Arrays.stream(values())
            .filter(e -> e.message.equals(errorMessage))
            .findFirst()
            .orElseThrow(() -> new RuntimeException(ErrorCode.INTERNAL_SERVER_ERROR.message()));
    }

    public String message() {
        return message;
    }
}
