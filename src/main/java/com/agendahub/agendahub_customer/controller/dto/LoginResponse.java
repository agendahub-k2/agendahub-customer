package com.agendahub.agendahub_customer.controller.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}