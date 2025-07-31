package com.controller;

import com.exceptions.PassagemJaCadastradaException;
import com.exceptions.PassagemNaoEncontradaException;
import com.model.Passagem;
import com.repository.IPassageiroRepository;
import com.repository.IPassagemRepository;
import com.repository.IVooRepository;
import com.repository.PassageiroRepository;
import com.repository.PassagemRepository;
import com.repository.VooRepository;

import java.util.List;

public class PassagemController {
    // Requisito 9: Singleton
    private static PassagemController instance;
    private final IPassagemRepository passagemRepository;
    private final IVooRepository vooRepository;
    private final IPassageiroRepository passageiroRepository;

    private PassagemController() {
        this.passagemRepository = PassagemRepository.getInstance();
        this.vooRepository = VooRepository.getInstance();
        this.passageiroRepository = PassageiroRepository.getInstance();
    }

    public static synchronized PassagemController getInstance() {
        if (instance == null) {
            instance = new PassagemController();
        }
        return instance;
    }

    public String comprarPassagem(Passagem passagem) {
        try {
            passagemRepository.adicionar(passagem);
            return "Passagem comprada com sucesso. Valor pago: R$ " + passagem.getPrecoPago();
        } catch (PassagemJaCadastradaException e) {
            return "Erro ao comprar passagem: " + e.getMessage();
        }
    }

    public Passagem buscarPassagemPorId(int id) {
        try {
            return passagemRepository.buscarPorId(id);
        } catch (PassagemNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public List<Passagem> listarTodasPassagens() {
        return passagemRepository.listarTodas();
    }

    public List<Passagem> buscarPassagensPorPassageiro(int idPassageiro) {
        return passagemRepository.buscarPorPassageiro(idPassageiro);
    }

    public String removerPassagem(int id) {
        try {
            passagemRepository.remover(id);
            return "Passagem removida com sucesso.";
        } catch (PassagemNaoEncontradaException e) {
            return "Erro ao remover passagem: " + e.getMessage();
        }
    }

}
