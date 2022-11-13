package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract  class Pessoa {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
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
    private LocalDateTime criado_em = LocalDateTime.now();

    @Getter
    @Setter
    @Column(length = 20, updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime alterado_em =  LocalDateTime.now();

    @Getter
    @Setter
    @Column(nullable = false)
    private boolean status = true;
}
