package com.elasbancam.dtos;

import com.elasbancam.enums.TipoOperacao;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class ContaDto {
    @Valid
    @Size(max = 20)
    private int numero_conta;

    @Valid
    @Size(max = 10)
    private int agencia;

    private TipoOperacao operacao;

    @Valid
    private BigDecimal saldo;

}