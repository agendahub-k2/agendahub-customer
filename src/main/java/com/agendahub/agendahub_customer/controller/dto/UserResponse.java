package com.agendahub.agendahub_customer.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String userType;
    private String createdAt;
    private String updateAt;
    private ProviderResponse providerResponse;
}
