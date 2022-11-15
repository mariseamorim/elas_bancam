package com.elasbancam.exceptions;

public class ContaNaoExistenteException extends Exception {
    public ContaNaoExistenteException(Object id) {
            super("Conta " + id +" Não Existe");
        }
}
