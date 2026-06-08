package com.lawlayui.library.api.dto.response;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponseDTO<ID> {
    protected ID id;
    protected Instant timestamp;
    protected LocalDateTime createdAt;
    protected LocalDateTime updateAt; 
}
