package com.lawlayui.library.api.dto.response;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<ID> {
    protected ID id;
    protected Instant timestamp;
    protected LocalDateTime createdAt;
    protected LocalDateTime updateAt; 
    protected String message;
}
