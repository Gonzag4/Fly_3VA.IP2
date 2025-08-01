package com.repository;

import com.exceptions.EmpresaAereaJaCadastradaException;
import com.exceptions.EmpresaAereaNaoEncontradaException;
import com.model.EmpresaAerea;
import java.util.List;

public interface IEmpresaAereaRepository {
    void cadastrar(EmpresaAerea empresa) throws EmpresaAereaJaCadastradaException;
    EmpresaAerea buscarPorId(int id) throws EmpresaAereaNaoEncontradaException;
    EmpresaAerea buscarPorLogin(String login) throws EmpresaAereaNaoEncontradaException;
    EmpresaAerea buscarPorCnpj(String cnpj) throws EmpresaAereaNaoEncontradaException;
    List<EmpresaAerea> listarTodos();
    void atualizar(EmpresaAerea empresa) throws EmpresaAereaNaoEncontradaException;
    void remover(int id) throws EmpresaAereaNaoEncontradaException;
    boolean testarLogin(String login, String senha);
}