package com.elasbancam.exceptions;

public class UpdateInvalidoException extends Exception {
    public UpdateInvalidoException(Object id) {
        super("O ID" + id +" Não Existe, Impossível Atualizar");
    }
}
