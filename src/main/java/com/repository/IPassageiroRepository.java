package com.repository;

import com.exceptions.PassageiroJaCadastradoException;
import com.exceptions.PassageiroNaoEncontradoException;
import com.model.Passageiro;
import java.util.List;

public interface IPassageiroRepository {
    void cadastrar(Passageiro passageiro) throws PassageiroJaCadastradoException;
    Passageiro buscarPorId(int id) throws PassageiroNaoEncontradoException;
    Passageiro buscarPorCpf(String cpf) throws PassageiroNaoEncontradoException;
    Passageiro buscarPorLogin(String login) throws PassageiroNaoEncontradoException;
    List<Passageiro> listarTodos();
    void atualizar(Passageiro passageiro) throws PassageiroNaoEncontradoException;
    void remover(int id) throws PassageiroNaoEncontradoException;
    boolean testarLogin(String login, String senha);
}