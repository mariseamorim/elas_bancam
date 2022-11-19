package com.elasbancam.models;

import com.elasbancam.enums.TipoOperacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

// Representa a tabela "conta"
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Getter
    @Setter
    @Column(insertable = false, unique = true, updatable = false, nullable = false)
    private String id;

    @Getter
    @Setter
    @Column(length = 20, unique = true, nullable = false, updatable = false)
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
    private boolean status = true;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "conta_origem")
    private List<Transacao> transacoesContaOrigem;

    @Getter
    @Setter
    @JsonIgnore
    @OneToMany(mappedBy = "conta_destino")
    private List<Transacao> transacoesContaDestino;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

