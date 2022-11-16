package com.elasbancam.models;

import com.elasbancam.enums.Genero;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Getter
@Setter
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends Pessoa{
    @Column(length = 20, nullable = false, unique = true)
    @CPF
    private String cpf;

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(length = 20, nullable = false, unique = true)
    private String rg;

    @Column(length = 20, nullable = false)
    @DateTimeFormat
    private LocalDate dt_nascimento;

    @Column(nullable = false)
    private String nome_mae;
    @NotBlank

    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Genero genero;
}
