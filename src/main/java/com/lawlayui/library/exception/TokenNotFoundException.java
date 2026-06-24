package com.lawlayui.library.exception;

public class TokenNotFoundException extends Exception {
    public TokenNotFoundException(String msg) {
        super(msg);
    }
    public TokenNotFoundException() {
        super("token not found please try logging in again");
    }
}
