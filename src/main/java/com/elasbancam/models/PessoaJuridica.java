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
    @Column(length = 50, nullable = false)
    private String nome_fantasia;
    private String inscricao_estadual;
    private String cnpj;
    private String contato;
}
