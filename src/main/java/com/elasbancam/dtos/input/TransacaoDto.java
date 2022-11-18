package com.elasbancam.dtos.input;

import com.elasbancam.enums.TipoTransacao;
import lombok.Getter;
import lombok.Setter;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TransacaoDto {

    private ContaTransacaoDto conta_origem;

    private ContaTransacaoDto conta_destino;

    @Valid
    @NotNull
    private TipoTransacao tipo_transacao;

    @NotNull
    private BigDecimal valor;

    private String descricao;
}
