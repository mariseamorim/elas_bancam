package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Pessoa pessoa;

    @Column(length = 20, nullable = false, updatable = false)
    private int numeroConta;

    @Column(length = 10, nullable = false, updatable = false)
    private int agencia;

    @Enumerated(EnumType.ORDINAL)
    private String operacao;

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(nullable = false, updatable = false)
    private boolean status;
}

