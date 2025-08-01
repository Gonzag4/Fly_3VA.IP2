package com.exceptions;

public class AeronaveNaoEncontradaException extends Exception {
    public AeronaveNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}