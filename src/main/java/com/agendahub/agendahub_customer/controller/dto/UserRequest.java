package com.agendahub.agendahub_customer.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Name cannot be empty or null")
    @Size(max = 100, message = "Name should not be longer than 100 characters")
    private String name;

    @NotBlank(message = "Email cannot be empty or null")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email should not be longer than 100 characters")
    private String email;

    @NotBlank(message = "Password cannot be empty or null")
    @Size(min = 6, max = 255, message = "Password should be between 6 and 255 characters")
    private String password;

    @NotBlank(message = "User type cannot be empty or null")
    private String userType;
}


