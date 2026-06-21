package com.lawlayui.library.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.request.UserUpdateRequest;
import com.lawlayui.library.api.dto.response.UserResponse;
import com.lawlayui.library.entity.User;
import com.lawlayui.library.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController<User, Long, UserResponse, UserCreateRequest, UserUpdateRequest> {
    public UserController(UserService service) {
        this.service = service;
    }
}
