package com.francisco.mock.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Integer idClient;

    @Column(name = "name")
    private String name;

    private String cpf;

    private String phone;

    private String phone2;

    private String email;

    @Column(name = "birth_date")
    private LocalDateTime data_nascimento;
}
