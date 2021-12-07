package com.shake_match.alchomist.global;

public class NotFoundException extends RuntimeException {
    public NotFoundException(ErrorCode message) {
        super(message.message());
    }
}
