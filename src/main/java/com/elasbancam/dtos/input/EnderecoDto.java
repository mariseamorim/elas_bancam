package com.elasbancam.dtos.input;

import com.elasbancam.enums.Regiao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EnderecoDto {
    @Valid
    @NotBlank
    @Size(max = 7)
    private String cep;

    @Valid
    @NotBlank
    private String rua;

    @Valid
    @NotBlank
    private int numero;

    @Valid
    @Size(max=20)
    private String complemento;

    @Valid
    @NotBlank
    private String bairro;

    @Valid
    @NotBlank
    private String cidade;

    @Valid
    @NotBlank
    @Size(max = 2)
    private String uf;

    @Valid
    @NotBlank
    @Size(max = 2)
    private Regiao regiao;
}
