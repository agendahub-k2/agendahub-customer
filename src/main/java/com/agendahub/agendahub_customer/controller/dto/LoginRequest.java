package com.agendahub.agendahub_customer.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Email cannot be empty or null")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email should not be longer than 100 characters")
    private String email;

    @NotBlank(message = "Password cannot be empty or null")
    @Size(max = 100, message = "Password should not be longer than 100 characters")
    private String password;

}
