package com.agendahub.agendahub_customer.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Provider {

    private Long id;

    private String numCep;

    private String descricaoRua;

    private String numero;

    private String typeProvider;
}
