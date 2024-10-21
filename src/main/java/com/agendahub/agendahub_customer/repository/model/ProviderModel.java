package com.agendahub.agendahub_customer.repository.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "providers")
@Getter
@Setter
@NoArgsConstructor
public class ProviderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num_cep", nullable = false, length = 100)
    private String numCep;

    @Column(name = "descricao_rua", nullable = false, length = 500)
    private String descricaoRua;

    @Column(name = "numero", nullable = false, length = 100)
    private String numero;

    @Column(name = "type_provider", nullable = false, length = 100)
    private String typeProvider;
}

