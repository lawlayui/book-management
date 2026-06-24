package com.lawlayui.library.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginRequest extends BaseRequest{
    @NotBlank(message = "email cannot be empty")
    @Email(message = "not valid email")
    private String email;

    @Size(max = 255, min = 7)
    @NotBlank(message = "password cannot be empty")
    private String password;
}
