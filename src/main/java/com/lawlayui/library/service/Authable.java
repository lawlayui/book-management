package com.lawlayui.library.service;

import java.util.List;

import com.lawlayui.library.api.dto.request.AuthLoginRequest;
import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.nimbusds.jwt.SignedJWT;

public interface Authable {
    default List<String> login(AuthLoginRequest authLoginRequest) throws Exception {return null;}
    default void register(UserCreateRequest userCreateRequest) throws Exception {return;}
    default String refresh(String uuidRefreshToken, SignedJWT signedJWT) throws Exception {return null;}
}
