package com.lawlayui.library.api.dto.response;

import java.time.LocalDateTime;
import java.time.Instant;

import com.lawlayui.library.entity.User.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse extends BaseResponse<Long>{

    @Builder
    public UserResponse(Long id, String name, String email, Role role, LocalDateTime updateAt) {
        super(id, Instant.now(), LocalDateTime.now(), updateAt, "success");

        this.name = name;
        this.email = email;
        this.role = role;
    }

    private String name;
    private String email;
    private Role role;
}
