package com.elasbancam.exceptions.validation;

import com.elasbancam.exceptions.NegocioException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ValidationAge {

    public void validarIdadeMenorQue18(LocalDate dtNascimento){
        long idade = ChronoUnit.YEARS.between(dtNascimento, LocalDate.now());

        if(idade< 18){
            throw new NegocioException("Abertura de conta permitida apenas para maiores de 18 anos!");
        }


    }
}
