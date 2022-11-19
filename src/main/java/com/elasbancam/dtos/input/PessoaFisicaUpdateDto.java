package com.elasbancam.dtos.input;

import com.elasbancam.enums.Genero;
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

    private EnderecoDto endereco;

    private String nome_mae;

    private Genero genero;
}
