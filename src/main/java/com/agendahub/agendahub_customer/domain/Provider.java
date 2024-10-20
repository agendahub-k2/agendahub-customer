package com.agendahub.agendahub_customer.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Provider {

    private Long id;

    private String nameProvider;

    private String numCep;

    private String descricaoRua;

    private String numero;

    private String typeProvider;
}
