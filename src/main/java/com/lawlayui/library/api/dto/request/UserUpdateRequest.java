package com.lawlayui.library.api.dto.request;


import com.lawlayui.library.entity.User.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserUpdateRequest extends BaseRequestDTO{
    @Size(max = 255, message = "the maximum name limit is 255 characters")
    private String name;

    @Email
    private String email;

    @Size(min = 8, message = "the minimum password limit is 255 characters")
    private String password;

    private Role role;
}
