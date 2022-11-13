package com.elasbancam.dtos.input;

import com.elasbancam.enums.TipoOperacao;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
