package com.lawlayui.library.service.impl;

import java.util.List;

import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.request.UserUpdateRequest;
import com.lawlayui.library.api.dto.response.UserResponse;
import com.lawlayui.library.entity.User;
import com.lawlayui.library.service.CanCrud;
import com.lawlayui.library.service.Service;

public class UserService implements CanCrud<User, UserResponse, UserCreateRequest, UserUpdateRequest, Long>, Service{

    @Override
    public String getCacheNames() {
        return "user";
    }

    @Override
    public List<UserResponse> findAll(int size, int page) throws Exception {
        // TODO Auto-generated method stub
        return CanCrud.super.findAll(size, page);
    }

    @Override
    public UserResponse findById(Long id) throws Exception {
        // TODO Auto-generated method stub
        return CanCrud.super.findById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        // TODO Auto-generated method stub
        CanCrud.super.delete(id);
    }

    @Override
    public UserResponse save(UserCreateRequest request) throws Exception {
        // TODO Auto-generated method stub
        return CanCrud.super.save(request);
    }

    @Override
    public UserResponse update(UserUpdateRequest request, Long id) throws Exception {
        // TODO Auto-generated method stub
        return CanCrud.super.update(request, id);
    }
    
}
