package com.elasbancam.exceptions;

// Criamos um tipo genérico de exceção que será chamado em cada validação feita nos services
public class NegocioException extends RuntimeException {
    public NegocioException(String mensagem){
        super(mensagem);
    }
}
