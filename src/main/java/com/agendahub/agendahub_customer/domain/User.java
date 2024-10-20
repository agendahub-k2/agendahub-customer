package com.agendahub.agendahub_customer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {
    private String id = null;
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private Provider provider;

    public enum UserType {
        SOLICITANTE,
        PROVEDOR
    }
}

