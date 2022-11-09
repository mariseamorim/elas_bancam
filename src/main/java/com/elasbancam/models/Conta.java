package com.elasbancam.models;

import com.elasbancam.models.enums.TipoOperacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "conta")
public class Conta {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private UUID id;

    @Getter
    @Setter
    @Column(length = 20, nullable = false, updatable = false)
    private int numeroConta;

    @Getter
    @Setter
    @Column(length = 10, nullable = false, updatable = false)
    private int agencia;

    @Getter
    @Setter
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private TipoOperacao operacao;

    @Getter
    @Setter
    @Column(nullable = false)
    private BigDecimal saldo;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean status;
}

