package com.lawlayui.library.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawlayui.library.api.dto.response.BaseResponse;

public interface ReadableController<RES extends BaseResponse<ID>, ID>{
    @GetMapping("/{id}")
    default ResponseEntity<RES> getById(@PathVariable ID id) throws Exception{
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }

    @GetMapping
    default ResponseEntity<List<RES>> getAll(@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "0") int page) throws Exception {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
    }
}
