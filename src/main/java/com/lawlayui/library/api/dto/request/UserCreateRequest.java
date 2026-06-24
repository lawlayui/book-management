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
public class UserCreateRequest extends BaseRequest{
    @NotBlank(message = "name cannot be filled with null")
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank(message = "email cannot be filled with null")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "password cannot be filled with null")
    @Size(min = 7, max = 255)
    private String password;
}
