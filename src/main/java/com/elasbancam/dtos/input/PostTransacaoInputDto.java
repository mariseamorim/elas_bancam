package com.elasbancam.dtos.input;

import com.elasbancam.enums.TipoTransacao;


import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;


public class PostTransacaoInputDto {
    @NotBlank(message = "Campo de ID da conta de origem deve ser preenchido")
    private String conta_origem_id;

    @NotBlank(message = "Campo de ID da conta de destino deve ser preenchido")
    private String conta_destino_id;

    @NotBlank(message = "Campo de tipo de transação deve ser preenchido")
    private TipoTransacao tipo_trasacao;

    @NotBlank(message = "Campo valor da transação deve ser preenchido")
    private BigDecimal valor;

    private String descricao;
}
