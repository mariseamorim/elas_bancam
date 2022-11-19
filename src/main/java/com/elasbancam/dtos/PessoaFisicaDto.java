package com.elasbancam.dtos;

import com.elasbancam.enums.Genero;
import lombok.Getter;
import lombok.Setter;


import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
public class PessoaFisicaDto {
    @Valid
    @Size(max = 20)
    @NotBlank
    private String cpf;

    @Valid
    @Size(max = 20)
    @NotBlank
    private String rg;

    @NotNull
    @Past
    private LocalDate dt_nascimento;

    @Valid
    @NotBlank
    @NotNull
    private String nome;

    @Valid
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String telefone;

    @NotNull
    private String celular;

    private EnderecoDto endereco;

    private ContaDto conta;

    @NotBlank
    private String nome_mae;

    private Genero genero;
}
