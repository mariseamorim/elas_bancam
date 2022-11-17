package com.elasbancam.exceptions;

public class CPFJaCadastradoException extends Exception {
    public CPFJaCadastradoException() {
        super("CPF Já Cadastrado.");
    }
}
