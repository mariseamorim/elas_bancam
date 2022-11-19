package com.elasbancam.dtos;

import com.elasbancam.enums.Genero;
import com.elasbancam.validation.ValueOfEnum;
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
    @NotNull
    private LocalDate dt_nascimento;

    @Valid
    @NotBlank
    private String nome;

    @Valid
    @NotBlank
    @Email
    private String email;

    @NotNull
    private String telefone;

    @NotNull
    private String celular;

    @Valid
    private EnderecoDto endereco;

    @NotBlank
    private String nome_mae;

    @ValueOfEnum(enumClass = Genero.class, message = "Valor inválido para genero, valores possiveis: MASCULINO, FEMININO, NAOBINARIO; ")
    private String genero;
}
