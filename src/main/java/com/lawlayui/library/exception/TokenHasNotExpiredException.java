package com.lawlayui.library.exception;

public class TokenHasNotExpiredException extends Exception {
    public TokenHasNotExpiredException(String message) {
        super(message);
    }
    public TokenHasNotExpiredException() {
        super("token has not expired or at least 5 minutes before expiration");
    }
}
