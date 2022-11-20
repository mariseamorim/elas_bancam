package com.elasbancam.models;

import com.elasbancam.enums.Genero;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Representa a tabela "pessoa_fisica"
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa{
    @Column(length = 20, nullable = false, unique = true)
    private String cpf;

    @Column(length = 20, nullable = false, unique = true)
    private String rg;

    @Column(length = 20, nullable = false)
    private LocalDate dt_nascimento;

    @Column(nullable = false)
    private String nome_mae;

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;

    public PessoaFisica(Long id, String nome, String email, String telefone, String celular, Endereco endereco, Conta conta, LocalDateTime criado_em, LocalDateTime alterado_em, boolean status, String cpf, String rg, LocalDate dt_nascimento, String nome_mae, Genero genero) {
        super(id, nome, email, telefone, celular, endereco, conta, criado_em, alterado_em, status);
        this.cpf = cpf;
        this.rg = rg;
        this.dt_nascimento = dt_nascimento;
        this.nome_mae = nome_mae;
        this.genero = genero;
    }



}
