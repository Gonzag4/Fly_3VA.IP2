package com.repository;


import com.model.Passagem;
import java.util.List;
import com.exceptions.PassagemJaCadastradaException;
import com.exceptions.PassagemNaoEncontradaException;


/**
 * Interface para o repositório de passagens
 * Requisito 7: Interface para desacoplamento entre camadas
 * Requisito 4: Define retornos usando List para trabalhar com coleções
 */
public interface IPassagemRepository {
    void adicionar(Passagem passagem) throws PassagemJaCadastradaException;
    Passagem buscarPorId(int id) throws PassagemNaoEncontradaException;
    List<Passagem> listarTodas(); // Requisito 4: Uso de coleção List
    List<Passagem> buscarPorPassageiro(int idPassageiro);
    void remover(int id) throws PassagemNaoEncontradaException;
}