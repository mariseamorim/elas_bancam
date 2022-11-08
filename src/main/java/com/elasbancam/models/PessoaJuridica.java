package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Pessoa{
    @Column(length = 100, nullable = false)
    private String nome_fantasia;

    @Column(length = 50, nullable = false)
    private String inscricao_estadual;

    @Column(length = 14, nullable = false)
    private String cnpj;

    @Column(length = 50, nullable = false)
    private String nome_contato;
}
