package com.elasbancam.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ContaTransacaoDto {
    @Valid
    @NotBlank
    private String id;
}
