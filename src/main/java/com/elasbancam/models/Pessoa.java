package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract  class Pessoa {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private int id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String telefone;

    @Column(length = 20, nullable = false)
    private String celular;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;

    @Column(length = 20, updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime criado_em;

    @Column(nullable = false)
    private boolean status;
}
