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

    private EndereDto endereco;

    @NotBlank
    private String nome_mae;

    private Genero genero;
}
