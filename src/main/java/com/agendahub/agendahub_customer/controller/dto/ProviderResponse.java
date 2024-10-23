package com.agendahub.agendahub_customer.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProviderResponse {

    private Long id;

    private String numCep;

    private String descricaoRua;

    private String numero;

    private String typeProvider;
}



