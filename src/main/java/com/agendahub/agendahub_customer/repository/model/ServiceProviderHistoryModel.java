package com.agendahub.agendahub_customer.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "services_history")
@Getter
@Setter
@NoArgsConstructor
public class ServiceProviderHistoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "provider_id", nullable = false)
    private Long providerId;

    @Column(name = "service_id", nullable = false)
    private Long serviceId;

    @Column(name = "name_service", nullable = false)
    private String nameService;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "duration", nullable = false)
    private BigDecimal duration;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "operation", nullable = false, updatable = false)
    private String operation;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


}
