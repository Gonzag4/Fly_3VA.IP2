package com.controller;

import com.exceptions.EmpresaAereaJaCadastradaException;
import com.exceptions.EmpresaAereaNaoEncontradaException;
import com.model.EmpresaAerea;
import com.repository.IEmpresaAereaRepository;
import com.repository.EmpresaAereaRepository;
import java.util.List;

public class EmpresaAereaController {
    private static EmpresaAereaController instance;
    private final IEmpresaAereaRepository repository;

    private EmpresaAereaController() {
        this.repository = EmpresaAereaRepository.getInstance();
    }

    public static synchronized EmpresaAereaController getInstance() {
        if (instance == null) {
            instance = new EmpresaAereaController();
        }
        return instance;
    }

    public void cadastrarEmpresa(String nome, String cnpj, String login, String senha)
            throws EmpresaAereaJaCadastradaException {
        EmpresaAerea empresa = new EmpresaAerea(nome, cnpj, login, senha);
        repository.cadastrar(empresa);
    }

    public void atualizarEmpresa(EmpresaAerea empresa) throws EmpresaAereaNaoEncontradaException {
        repository.atualizar(empresa);
    }

    public EmpresaAerea buscarEmpresaPorId(int id) throws EmpresaAereaNaoEncontradaException {
        return repository.buscarPorId(id);
    }

    public EmpresaAerea buscarEmpresaPorCnpj(String cnpj) throws EmpresaAereaNaoEncontradaException {
        return repository.buscarPorCnpj(cnpj);
    }

    public List<EmpresaAerea> listarTodasEmpresas() {
        return repository.listarTodos();
    }

    public EmpresaAerea buscarPorLogin(String login) throws EmpresaAereaNaoEncontradaException {
        return repository.buscarPorLogin(login);
    }

    public boolean testarLogin(String login, String senha) {
        return repository.testarLogin(login, senha);
    }

    public void removerEmpresa(int id) throws EmpresaAereaNaoEncontradaException {
        repository.remover(id);
    }
}