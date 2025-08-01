package com.exceptions;

public class EmpresaAereaNaoEncontradaException extends Exception {
    public EmpresaAereaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}