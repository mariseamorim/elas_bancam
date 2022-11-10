package com.elasbancam.dtos.output;

import com.elasbancam.enums.TipoTransacao;
import com.elasbancam.models.Conta;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PostTransacaoOutputDto {
    private String id;

    private Conta conta_origem_id;

    private Conta conta_destino_id;

    private TipoTransacao tipo_trasacao;

    private LocalDateTime data;

    private BigDecimal valor;

    private String descricao;
}
