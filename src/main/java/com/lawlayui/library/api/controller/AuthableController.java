package com.lawlayui.library.api.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lawlayui.library.api.dto.request.BaseRequest;
import com.lawlayui.library.api.dto.response.BaseResponse;

import jakarta.validation.Valid;

public interface AuthableController<RES extends BaseResponse<ID>, REGISTER_RESP extends BaseResponse<ID>, REQ extends BaseRequest, REGISTER_REQUEST extends BaseRequest, ID> {
    @PostMapping("/login")
    default ResponseEntity<RES> login(@Valid REQ request) {return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();}

    @PostMapping("/register")
    default ResponseEntity<REGISTER_RESP> register(@Valid REGISTER_REQUEST request) {return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();}

    @GetMapping("/refresh")
    default ResponseEntity<?> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String AuthHeader, @CookieValue(name = "refreshTokenUuid") String refreshTokenUuid) {return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();}
}
