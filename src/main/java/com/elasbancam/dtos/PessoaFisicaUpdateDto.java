package com.elasbancam.dtos;

import com.elasbancam.enums.Genero;
import com.elasbancam.exceptions.validation.ValueOfEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaFisicaUpdateDto {
    @Valid
    @NotNull
    private Long id;

    @Past
    private LocalDate dt_nascimento;

    @Valid
    private String nome;

    @Valid
    @Email
    private String email;

    private String telefone;

    private String celular;

    @Valid
    private EnderecoDto endereco;

    private String nome_mae;

    @ValueOfEnum(enumClass = Genero.class, message = "Valor inválido para genero, valores possiveis: MASCULINO, FEMININO, NAOBINARIO; ")
    private String genero;
}
