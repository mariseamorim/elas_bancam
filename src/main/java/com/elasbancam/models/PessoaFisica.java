package com.elasbancam.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dt_nascimento;
}
