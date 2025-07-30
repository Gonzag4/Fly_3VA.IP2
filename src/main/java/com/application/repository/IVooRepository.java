package com.application.repository;

import com.application.model.Voo;
import java.util.List;

/**
 * Interface para o repositório de voos
 * Requisito 7: Interface para desacoplamento entre camadas
 * Requisito 4: Define retornos usando List para trabalhar com coleções
 */
public interface IVooRepository {
    void adicionar(Voo voo);
    Voo buscarPorNumero(String numeroVoo);
    List<Voo> listarTodos(); // Requisito 4: Uso de coleção List
    void atualizar(Voo voo);
    void remover(String numeroVoo);
}