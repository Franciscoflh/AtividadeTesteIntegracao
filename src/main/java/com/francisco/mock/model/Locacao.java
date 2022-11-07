package com.francisco.mock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Locacao {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_location")
    private Integer locacaoId;

    @Column(name = "id_imovel")
    private Integer idImovel;

    @Column(name = "cliente_id")
    private Integer idClient;

    @Column(name = "valor_aluguel")
    private Double valorAluguel;

    @Column(name = "percentual_multa")
    private Double percentualMulta;

    @Column(name = "dia_vencimento")
    private Integer diaVencimento;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    private Boolean ativo;

    private String obs;
}
