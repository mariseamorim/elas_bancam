package com.elasbancam.exceptions;

public class NegocioException extends RuntimeException {
    public NegocioException(String mensagem){
        super(mensagem);
    }
}
