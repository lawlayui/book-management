package com.lawlayui.library.api.controller.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawlayui.library.api.controller.CrudController;
import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.request.UserUpdateRequest;
import com.lawlayui.library.api.dto.response.UserResponse;
import com.lawlayui.library.entity.User;
import com.lawlayui.library.service.CanCrud;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController implements CrudController<UserResponse, UserCreateRequest, UserUpdateRequest, Long>{

    private final CanCrud<User, UserResponse, UserCreateRequest, UserUpdateRequest, Long> userService;

    public UserController(CanCrud<User, UserResponse, UserCreateRequest, UserUpdateRequest, Long> userService) {
        this.userService = userService;
    }
    @Override
    public ResponseEntity<UserResponse> create(@Valid UserCreateRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(request));
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAll(int size, int page) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(size, page));
    }

    @Override
    public ResponseEntity<UserResponse> getById(Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) throws Exception {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<UserResponse> update(@Valid UserUpdateRequest updateRequest, Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(updateRequest, id));
    }
    
}
