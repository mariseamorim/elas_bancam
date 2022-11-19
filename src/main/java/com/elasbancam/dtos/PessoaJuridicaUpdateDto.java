package com.elasbancam.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Validated
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
    @Email
    private String email;

    @Valid
    @NotBlank
    private String telefone;

    @Valid
    @NotBlank
    private String celular;

    @Valid
    private EnderecoDto endereco;
}
