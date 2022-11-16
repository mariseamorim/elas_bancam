package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract  class Pessoa {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column(length = 50, nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(length = 50, nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(length = 20, nullable = false)
    private String telefone;

    @Getter
    @Setter
    @Column(length = 20, nullable = false)
    private String celular;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;

    @Getter
    @Setter
    @Column(length = 20, updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime criado_em;

    @Getter
    @Setter
    @Column(length = 20, updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime alterado_em;

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean status = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
