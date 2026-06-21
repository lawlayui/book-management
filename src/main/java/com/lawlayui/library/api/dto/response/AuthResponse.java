package com.lawlayui.library.api.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse extends BaseResponseDTO<Long>{
    public AuthResponse(Long id, String token) {
        super(id, Instant.now(), LocalDateTime.now(), LocalDateTime.now(), "success login");
        this.token = token;
    }
    private String token;
}
