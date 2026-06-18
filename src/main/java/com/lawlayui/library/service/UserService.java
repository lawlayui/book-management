package com.lawlayui.library.service;

import org.springframework.stereotype.Service;

import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.request.UserUpdateRequest;
import com.lawlayui.library.api.dto.response.UserResponse;
import com.lawlayui.library.entity.User;
import com.lawlayui.library.exception.ResourceNotFound;
import com.lawlayui.library.repository.UserRepository;
import com.lawlayui.library.util.mapper.UserMapper;

import jakarta.transaction.Transactional;

@Service
public class UserService extends BaseService<User, Long, UserResponse, UserCreateRequest, UserUpdateRequest>{


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.repository = userRepository;
        this.mapper = userMapper;
    }
    @Override
    public String getCacheNames() {
        return "users";
    }

    @Transactional
    @Override
    public UserResponse update(Long id, UserUpdateRequest request) throws ResourceNotFound {
        User user = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Resouce with id: " + id + " not found"));

        mapper.updateEntityFromDto(request, user);
        return mapper.entityToResponse(repository.save(user));
    }
    
}
