package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(length = 10, nullable = false)
    private String cep;

    @Column(length = 255, nullable = false)
    private String rua;

    @Column(nullable = false)
    private int numero;

    @Column(length = 20)
    private String complemento;

    @Column(length = 255)
    private String bairro;

    @Column(length = 255)
    private String cidade;

    @Column(length = 2)
    private String uf;

    @Column(length = 2)
    private String regiao;

    @OneToOne(mappedBy = "endereco")
    private Pessoa cliente;
}
