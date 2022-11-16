package com.elasbancam.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContaTransacaoDto {
    @Valid
    @NotNull
    private String id;
}
