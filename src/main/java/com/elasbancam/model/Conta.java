package com.elasbancam.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Conta {
    @Id
    private UUID id;
    // precisa da chave estrangeira para instanciar o id titular
    private int numeroConta;
    private int agencia;
    private int operacao;
    // decidir se será tipo double(mais simples com menor precisão) ou BigDecimal(mais complexo e maior precisão)
    private BigDecimal saldo;
}

