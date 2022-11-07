package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa{
    @Column(length = 20, nullable = false)
    private String cpf;
    @Column(length = 20, nullable = false)
    private String rg;
    @Column(length = 20, nullable = false)
    private Date dt_nascimento;
}
