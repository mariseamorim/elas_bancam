package com.elasbancam.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class DataTransacaoDto {
    @NotNull(message = "Campo data inicial deve ser preenchido")
    private LocalDate dataInicial;

    @NotNull(message = "Campo data final deve ser preenchido")
    private LocalDate dataFinal;
}
