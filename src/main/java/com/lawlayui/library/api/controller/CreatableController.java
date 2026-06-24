package com.lawlayui.library.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawlayui.library.api.dto.request.BaseRequest;
import com.lawlayui.library.api.dto.response.BaseResponse;

import jakarta.validation.Valid;

public interface CreatableController<RES extends BaseResponse<ID>, REQ extends BaseRequest, ID>{
    @PostMapping
    default ResponseEntity<RES> create(@Valid @RequestBody REQ request) throws Exception{
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
