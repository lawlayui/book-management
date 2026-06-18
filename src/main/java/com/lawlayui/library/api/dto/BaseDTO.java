package com.lawlayui.library.api.dto;

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
public class BaseDTO<ID> {
    protected ID id;
    protected Instant timestamp;
    protected LocalDateTime createdAt;
    protected LocalDateTime updateAt; 
}
