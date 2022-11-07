package com.francisco.mock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Imovel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "imovel_id")
    private Integer idImovel;

    @Column(name = "tipo_imovel")
    private String tipoImovel;

    private String endereco;

    private String bairro;

    private Integer cep;

    private Double metragem;

    private Integer dormitorios;

    private Integer banheiros;

    private Integer suites;

    @Column(name = "vagas_garagem")
    private Integer vagasGaragem;

    @Column(name = "valor_aluguel_sugerido")
    private Double valorAluguelSugerido;

    private String obs;
}
