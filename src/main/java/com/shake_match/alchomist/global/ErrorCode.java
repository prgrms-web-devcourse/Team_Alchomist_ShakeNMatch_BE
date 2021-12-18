package com.shake_match.alchomist.global;

import java.util.Arrays;

public enum ErrorCode {
    INVALID_MEMBER_NAME("이름 형식이 맞지 않습니다."),
    INVALID_MEMBER_EMAIL("이메일 형식이 맞지 않습니다."),
    INVALID_MEMBER_AGE("나이 형식이 맞지 않습니다."),
    INVALID_MEMBER_HOBBY("취미 형식이 맞지 않습니다."),
    NOT_EXIST_MEMBER("존재하지 않는 회원입니다."),
    NOT_EXIST_NICKNAME("존재하지 않는 닉네임입니다."),
//    NOT_EXIST_BOOKMARK("존재하지 않는 북마크입니다."),
    INTERNAL_SERVER_ERROR("정의되지 않은 서버 에러"),
    DUPLICATION_MEMBER_EMAIL("중복된 이메일입니다."),
    DUPLICATION_MEMBER_NICKNAME("이미 존재하는 닉네임입니다."),
    INVALID_POST_TITLE("제목 형식이 맞지 않습니다."),
    NOT_EXIST_POST("존재하지 않는 게시글 입니다."),
    NOT_EXIST_COCKTAIL("존재하지 않는 칵테일 입니다."),
    INVALID_POST_STATUS("존재하지 않는 게시글 상태 입니다."),
    INVALID_IMAGE_URL("이미지의 URL 형식이 맞지 않습니다."),
    INVALID_IMAGE_TYPE("이미지의 종류 형식이 맞지 않습니다."),
    NOT_EXIST_IMAGE("존재하지 않는 이미지입니다"),
    NOT_EXIST_INGREDIENT("존재하지 않는 재료입니다"),
    NOT_EXIST_BADGE("존재하지 않는 배지입니다"),
    NOT_EXIST_REVIEW("존재하지 않는 리뷰입니다"),
    UNSUPPORTED_MEDIA_TYPE("지원하지 않는 미디어 타입입니다."),
    NOT_EXIST_LIKE("존재하지 않는 좋아요 입니다."),
    TOO_MANY_REVIEW("리뷰 전체 조회 시, 리뷰의 개수가 지정된 개수보다 많습니다."),
    TOO_MANY_INGREDIENT("재료 전체 조회 시, 재료의 개수가 지정된 개수보다 많습니다."),
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
