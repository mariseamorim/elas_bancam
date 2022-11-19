package com.elasbancam.dtos;

import com.elasbancam.enums.TipoOperacao;
import com.elasbancam.validation.ValueOfEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class ContaDto {
    @Valid
    @Max(value = Integer.MAX_VALUE)
    private int numero_conta;

    @Valid
    @NotNull
    @Max(value = Integer.MAX_VALUE)
    private int agencia;

    @Valid
    @NotBlank
    @ValueOfEnum(enumClass = TipoOperacao.class, message = "Valor inválido para operacao,valores possiveis: CONTAPOUPANCA, CONTACORRENTEPF, CONTACORRENTEPJ ")
    private String operacao;

    @Valid
    private BigDecimal saldo;

}
