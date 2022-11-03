package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tab_endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @Column(length = 20, nullable = false)
    private String cep;
    @Column(length = 10, nullable = false)
    private String numero;
    @Column(length = 20)
    private String complemento;
    @Column(length = 20)
    private String bairro;
    @Column(length = 20)
    private String cidade;
    @Column(length = 10)
    private String uf;
    @Column(length = 15)
    private String regiao;
    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;
}
