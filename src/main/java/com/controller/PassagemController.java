package com.controller;

import com.exceptions.*;
import com.model.Passageiro;
import com.model.Passagem;
import com.model.Voo;
import com.repository.IPassageiroRepository;
import com.repository.IPassagemRepository;
import com.repository.IVooRepository;
import com.repository.PassageiroRepository;
import com.repository.PassagemRepository;
import com.repository.VooRepository;
import java.util.List;

public class PassagemController {
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

    public void comprarPassagem(Passagem passagem)
            throws PassagemJaCadastradaException, VooNaoEncontradoException,
            PassageiroNaoEncontradoException, VooLotadoException {

        // Validações
        Passageiro passageiro = passageiroRepository.buscarPorId(passagem.getPassageiro().getId());
        Voo voo = vooRepository.buscarPorNumero(passagem.getVoo().getNumeroVoo());

        if (voo.getAssentosDisponiveis() <= 0) {
            throw new VooLotadoException("Não há assentos disponíveis neste voo");
        }

        passagemRepository.adicionar(passagem);

        if (!passagem.reservar()) {
            throw new VooLotadoException("Falha ao reservar passagem");
        }
    }

    public Passagem buscarPassagemPorId(int id) throws PassagemNaoEncontradaException {
        return passagemRepository.buscarPorId(id);
    }

    public List<Passagem> listarTodasPassagens() {
        return passagemRepository.listarTodas();
    }

    public List<Passagem> buscarPassagensPorPassageiro(int idPassageiro)
            throws PassageiroNaoEncontradoException {
        passageiroRepository.buscarPorId(idPassageiro); // Valida se existe
        return passagemRepository.buscarPorPassageiro(idPassageiro);
    }

    public void removerPassagem(int id) throws PassagemNaoEncontradaException {
        passagemRepository.remover(id);
    }
}