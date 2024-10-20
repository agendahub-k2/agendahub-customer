package com.agendahub.agendahub_customer.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ServiceProvedor {

    private Long id;
    private String nameService;
    private BigDecimal duration;
    private BigDecimal price;
    private String createdAt;
    private String updateAt;
    private Long providerId;
}
