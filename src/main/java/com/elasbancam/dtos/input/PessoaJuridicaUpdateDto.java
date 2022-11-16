package com.elasbancam.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PessoaJuridicaUpdateDto {
    @Valid
    @NotNull
    private Long id;

    @Valid
    @Size(max = 100)
    @NotBlank
    private String nome_fantasia;

    @Valid
    @Size(max = 50)
    @NotBlank
    private String nome_contato;

    @Valid
    @NotBlank
    @NotNull
    private String nome;

    @Valid
    @NotBlank
    @NotNull
    @Email
    private String email;

    @Valid
    @NotNull
    private String telefone;

    @Valid
    @NotNull
    private String celular;

    private EndereDto endereco;
}
