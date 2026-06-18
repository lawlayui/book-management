package com.lawlayui.library.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.lawlayui.library.api.dto.request.UserCreateRequest;
import com.lawlayui.library.api.dto.request.UserUpdateRequest;
import com.lawlayui.library.api.dto.response.UserResponse;
import com.lawlayui.library.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends GenericMapper<Long, User, UserCreateRequest, UserUpdateRequest, UserResponse> {
    
}
