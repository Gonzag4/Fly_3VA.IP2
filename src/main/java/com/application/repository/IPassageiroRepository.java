package com.application.repository;

import com.application.exceptions.PassageiroJaCadastradoException;
import com.application.exceptions.PassageiroNaoEncontradoException;
import com.application.model.Passageiro;
import java.util.List;


/**
 * Interface para o repositório de passageiros
 * Requisito 7: Define a interface para desacoplamento entre camadas
 * Requisito 4: Tipo de retorno List para uso com coleções
 */
public interface IPassageiroRepository {

    // Requisito 6: Método pode lançar exceção para tratamento de erros
    void cadastrar(Passageiro passageiro) throws PassageiroJaCadastradoException;
    Passageiro buscarPorId(int id) throws PassageiroNaoEncontradoException;
    Passageiro buscarPorCpf(String cpf) throws PassageiroNaoEncontradoException;
    List<Passageiro> listarTodos();  // Requisito 4: Uso de coleção List
    void atualizar(Passageiro passageiro) throws PassageiroNaoEncontradoException;
    void remover(int id) throws PassageiroNaoEncontradoException;
}