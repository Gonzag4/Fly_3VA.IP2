package com.controller;

import com.controller.PassageiroController;
import com.controller.PassagemController;
import com.controller.VooController;
import com.exceptions.*;
import com.model.Passageiro;
import com.model.Passagem;
import com.model.Voo;
import com.model.VooInternacional;
import com.model.VooNacional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Fachada do sistema que implementa o padrão Facade para fornecer uma interface única
 * e simplificada para o sistema de vendas de passagens aéreas.
 *
 * Requisito 7: Arquitetura em camadas (MVC) - Esta classe atua como intermediária entre
 * a view e os controllers, isolando a complexidade do sistema.
 *
 * Requisito 9: Uso de Singleton - Implementado como Singleton para garantir uma única instância.
 */
public class SistemaVendasPassagens {
    private static SistemaVendasPassagens instance;
    private final PassageiroController passageiroController;
    private final VooController vooController;
    private final PassagemController passagemController;

    private SistemaVendasPassagens() {
        this.passageiroController = PassageiroController.getInstance();
        this.vooController = VooController.getInstance();
        this.passagemController = PassagemController.getInstance();
    }

    public static synchronized SistemaVendasPassagens getInstance() {
        if (instance == null) {
            instance = new SistemaVendasPassagens();
        }
        return instance;
    }

    public void cadastrarPassageiro(String nome, String cpf, String telefone) throws PassageiroJaCadastradoException {
        passageiroController.cadastrarPassageiro(nome, cpf, telefone);
    }

    public Passageiro buscarPassageiro(int id) throws PassageiroNaoEncontradoException {
        return passageiroController.buscarPassageiroPorId(id);
    }

    public List<Passageiro> listarPassageiros() {
        return passageiroController.listarTodosPassageiros();
    }

    public void cadastrarVooNacional(String numeroVoo, String origem, String destino,
                                     LocalDateTime dataHora, double precoBase,
                                     int assentos, double taxaEmbarque) throws VooJaCadastradoException {
        vooController.cadastrarVooNacional(numeroVoo, origem, destino, dataHora,
                precoBase, assentos, taxaEmbarque);
    }

    public void cadastrarVooInternacional(String numeroVoo, String origem, String destino,
                                          LocalDateTime dataHora, double precoBase,
                                          int assentos, double taxaEmbarque,
                                          double taxaAlfandega) throws VooJaCadastradoException {
        vooController.cadastrarVooInternacional(numeroVoo, origem, destino, dataHora,
                precoBase, assentos, taxaEmbarque, taxaAlfandega);
    }

    public Voo buscarVoo(String numeroVoo) throws VooNaoEncontradoException {
        return vooController.buscarVoo(numeroVoo);
    }

    public List<Voo> listarVoos() {
        return vooController.listarTodosVoos();
    }
    
    public double calcularPrecoVoo(String numeroVoo) throws VooNaoEncontradoException {
        return vooController.calcularPrecoFinalVoo(numeroVoo);
    }

    /*
     * Métodos para manipulação de passagens
     * Requisito 6: Tratamento de exceções - Lança várias exceções específicas
     * Requisito 8: JavaFX - Interface pronta para integração com a view
     */
    public void comprarPassagem(Passageiro passageiro, Voo voo, String assento)
            throws PassagemJaCadastradaException, VooNaoEncontradoException,
            PassageiroNaoEncontradoException, VooLotadoException, AssentoOcupadoException {
        Passagem passagem = new Passagem(passageiro, voo, assento);
        passagemController.comprarPassagem(passagem);
    }

    public List<Passagem> listarPassagens() {
        return passagemController.listarTodasPassagens();
    }

    public List<Passagem> buscarPassagensPorPassageiro(int idPassageiro) throws PassageiroNaoEncontradoException {
        return passagemController.buscarPassagensPorPassageiro(idPassageiro);
    }
}