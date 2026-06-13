package com.lawlayui.library.api.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawlayui.library.api.dto.request.BaseRequestDTO;
import com.lawlayui.library.api.dto.response.BaseResponseDTO;
import com.lawlayui.library.entity.BaseEntity;
import com.lawlayui.library.exception.ResourceNotFound;
import com.lawlayui.library.service.BaseService;

import jakarta.validation.constraints.Max;

public abstract class BaseController<T extends BaseEntity<ID>, ID, RESP extends BaseResponseDTO<ID>, REQ extends BaseRequestDTO, UREQ extends BaseRequestDTO> {
    protected BaseService<T, ID, RESP, REQ, UREQ> service;

    @GetMapping
    public ResponseEntity<List<RESP>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") @Max(value = 43, message = "Max for size is 43") int size) {
        List<RESP> responses = service.findAll(PageRequest.of(page, size));
        return ResponseEntity
            .ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RESP> getById(@PathVariable ID id) throws ResourceNotFound{
        RESP resp = service.findById(id);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<RESP> createEntity(@RequestBody REQ request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(service.save(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RESP> updateEntity(@PathVariable ID id, @RequestBody UREQ request) throws ResourceNotFound{
        RESP resp = service.update(id, request);
        return ResponseEntity.status(200)
            .body(resp);
    }
}
