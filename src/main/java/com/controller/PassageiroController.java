package com.controller;

import com.exceptions.PassageiroJaCadastradoException;
import com.exceptions.PassageiroNaoEncontradoException;
import com.model.Passageiro;
import com.repository.IPassageiroRepository;
import com.repository.PassageiroRepository;
import java.util.List;

public class PassageiroController {
    // Requisito 9: Singleton
    private static PassageiroController instance;
    private final IPassageiroRepository repository;

    private PassageiroController() {
        this.repository = PassageiroRepository.getInstance();
    }

    public static synchronized PassageiroController getInstance() {
        if (instance == null) {
            instance = new PassageiroController();
        }
        return instance;
    }

    public void cadastrarPassageiro(String nome, String cpf, String telefone, String login, String senha) throws PassageiroJaCadastradoException {
        Passageiro novoPassageiro = new Passageiro(nome, cpf, telefone, login, senha);
        repository.cadastrar(novoPassageiro);
    }

    public Passageiro buscarPassageiroPorId(int id) throws PassageiroNaoEncontradoException {
        return repository.buscarPorId(id);
    }

    public Passageiro buscarPassageiroPorCpf(String cpf) throws PassageiroNaoEncontradoException {
        return repository.buscarPorCpf(cpf);
    }

    public List<Passageiro> listarTodosPassageiros() {
        return repository.listarTodos();
    }

    public void atualizarPassageiro(Passageiro passageiro) throws PassageiroNaoEncontradoException {
        repository.atualizar(passageiro);
    }

    public void removerPassageiro(int id) throws PassageiroNaoEncontradoException {
        repository.remover(id);
    }

    public boolean testarLogin(String login, String senha) {
        try {
            repository.buscarPorLoginESenha(login, senha);
            return true;
        } catch (PassageiroNaoEncontradoException e) {
            return false;
        }
    }
}