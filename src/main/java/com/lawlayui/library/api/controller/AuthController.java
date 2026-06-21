package com.lawlayui.library.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawlayui.library.api.dto.request.AuthLoginRequest;
import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.response.AuthResponse;
import com.lawlayui.library.exception.EmailAlreadyExistsException;
import com.lawlayui.library.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> getToken(@RequestBody AuthLoginRequest dto ) {
        return ResponseEntity.status(200).body(new AuthResponse(null, authService.login(dto)));
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UserCreateRequest dto) throws EmailAlreadyExistsException{
        authService.registerUser(dto);
        return ResponseEntity.status(200).body("create a user succes");
    }
    
}
