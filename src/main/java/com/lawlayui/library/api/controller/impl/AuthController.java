package com.lawlayui.library.api.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawlayui.library.api.controller.AuthableController;
import com.lawlayui.library.api.dto.request.AuthLoginRequest;
import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.response.AuthResponse;
import com.lawlayui.library.api.dto.response.UserResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthableController<AuthResponse, UserResponse, AuthLoginRequest, UserCreateRequest, Long>{

    @Override
    public ResponseEntity<AuthResponse> login(@Valid AuthLoginRequest request) {
        // TODO Auto-generated method stub
        return AuthableController.super.login(request);
    }

    @Override
    public ResponseEntity<?> refreshToken(String AuthHeader, String refreshTokenUuid) {
        // TODO Auto-generated method stub
        return AuthableController.super.refreshToken(AuthHeader, refreshTokenUuid);
    }

    @Override
    public ResponseEntity<UserResponse> register(@Valid UserCreateRequest request) {
        // TODO Auto-generated method stub
        return AuthableController.super.register(request);
    }
    
}
