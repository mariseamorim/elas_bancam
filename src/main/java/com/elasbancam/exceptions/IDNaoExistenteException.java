package com.elasbancam.exceptions;

public class IDNaoExistenteException extends Exception {
    public IDNaoExistenteException(Object id) {
            super("Conta " + id +" Não Existe");
        }
}
