package com.elasbancam.models;

import com.elasbancam.models.enums.Genero;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(nullable = false)
    private String nome_mae;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Genero genero;
}
