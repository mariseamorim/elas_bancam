package com.elasbancam.exceptions;

public class CNPJJaCadastradoException extends Exception {
    public CNPJJaCadastradoException() {
        super("CPF Já Cadastrado.");
    }
}
