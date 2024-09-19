package com.agendahub.agendahub_customer.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String name;
    private String email;
    private String userType;
    private String createdAt;
    private String updateAt;
}
