package com.elasbancam.models;

import com.elasbancam.enums.TipoOperacao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "conta")
public class Conta {
    @Getter
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(insertable = false, unique = true, updatable = false, nullable = false)
    private String id;

    @Getter
    @Setter
    @Column(length = 20, nullable = false, updatable = false)
    private int numero_conta;

    @Getter
    @Setter
    @Column(length = 10, nullable = false, updatable = false)
    private int agencia;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
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

