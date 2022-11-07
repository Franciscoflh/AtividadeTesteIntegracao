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
public class Aluguel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "rent_id")
    private Integer aluguelId;

    @Column(name = "id_location")
    private Integer locacaoId;

    @Column(name = "due_date")
    private LocalDateTime dataVencimento;

    @Column(name = "amount_paid")
    private Double valorPago;

    @Column(name = "payment_date")
    private LocalDateTime dataPagamento;

    private String obs;

}
