package com.agendahub.agendahub_customer.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProviderRequest {

    private Long id;

    @NotBlank(message = "numCep cannot be empty or null")
    @Size(max = 100, message = "Cep Provider should not be longer than 100 characters")
    private String numCep;

    @NotBlank(message = "descricaoRua cannot be empty or null")
    @Size(max = 500, message = "Descricao cep should not be longer than 500 characters")
    private String descricaoRua;

    @NotBlank(message = "numero cannot be empty or null")
    @Size(max = 100, message = "numero cep should not be longer than 100 characters")
    private String numero;

    @NotBlank(message = "typeProvider cannot be empty or null")
    @Size(max = 100, message = "Type Provider should not be longer than 100 characters")
    private String typeProvider;
}



