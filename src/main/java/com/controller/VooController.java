package com.controller;

import com.exceptions.VooJaCadastradoException;
import com.exceptions.VooNaoEncontradoException;
import com.model.Voo;
import com.model.VooInternacional;
import com.model.VooNacional;
import com.repository.IVooRepository;
import com.repository.VooRepository;
import java.time.LocalDateTime;
import java.util.List;

public class VooController {
    // Requisito 9: Singleton
    private static VooController instance;
    private final IVooRepository repository;

    private VooController() {
        this.repository = VooRepository.getInstance();
    }

    public static synchronized VooController getInstance() {
        if (instance == null) {
            instance = new VooController();
        }
        return instance;
    }

    public void cadastrarVooNacional(String numeroVoo, String origem, String destino,
                                     LocalDateTime dataHora, double precoBase,
                                     int assentosDisponiveis, double taxaEmbarque) throws VooJaCadastradoException {
        Voo voo = new VooNacional(numeroVoo, origem, destino, dataHora, precoBase, assentosDisponiveis, taxaEmbarque);
        repository.adicionar(voo);
    }

    public void cadastrarVooInternacional(String numeroVoo, String origem, String destino,
                                          LocalDateTime dataHora, double precoBase,
                                          int assentosDisponiveis, double taxaEmbarque,
                                          double taxaAlfandega) throws VooJaCadastradoException {
        Voo voo = new VooInternacional(numeroVoo, origem, destino, dataHora, precoBase, assentosDisponiveis, taxaEmbarque, taxaAlfandega);
        repository.adicionar(voo);
    }

    public Voo buscarVoo(String numeroVoo) throws VooNaoEncontradoException {
        return repository.buscarPorNumero(numeroVoo);
    }

    public List<Voo> listarTodosVoos() {
        return repository.listarTodos();
    }

    public void removerVoo(String numeroVoo) throws VooNaoEncontradoException {
        repository.remover(numeroVoo);
    }

    // Requisito 2: Polimorfismo (método genérico para qualquer tipo de Voo)
    public double calcularPrecoFinalVoo(String numeroVoo) throws VooNaoEncontradoException {
        Voo voo = repository.buscarPorNumero(numeroVoo);
        return voo.calcularPrecoFinal(); // Chama a implementação específica (nacional/internacional)
    }

    public void atualizar(Voo voo) throws VooNaoEncontradoException {
        repository.atualizar(voo);
    }
}