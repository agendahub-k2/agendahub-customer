package com.agendahub.agendahub_customer.repository.model;

import com.agendahub.agendahub_customer.domain.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserModel {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "tenant_id", nullable = false)
    private Integer tenantId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private User.UserType userType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "update_at", nullable = false, updatable = false)
    private LocalDateTime updateAt;

    // algo legal
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//    }

}
