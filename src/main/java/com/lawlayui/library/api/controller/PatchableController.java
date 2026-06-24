package com.lawlayui.library.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.lawlayui.library.api.dto.request.BaseRequest;
import com.lawlayui.library.api.dto.response.BaseResponse;

import jakarta.validation.Valid;

public interface PatchableController<RES extends BaseResponse<ID>, UREQ extends BaseRequest, ID>{
    @PatchMapping("/{id}")
    default ResponseEntity<RES> update(@Valid @RequestBody UREQ updateRequest, @PathVariable ID id) throws Exception{
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
