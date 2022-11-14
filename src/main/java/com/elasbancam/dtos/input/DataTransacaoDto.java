package com.elasbancam.dtos.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class DataTransacaoDto {
    @NotBlank(message = "Campo data inicial deve ser preenchido")
    private LocalDate dataInicial;

    @NotBlank(message = "Campo data final deve ser preenchido")
    private LocalDate dataFinal;
}
