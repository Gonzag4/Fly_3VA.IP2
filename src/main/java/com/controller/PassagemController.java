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
import java.util.ArrayList;
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


    //estava dando erro - correção:
    public void comprarPassagem(Passagem passagem)
            throws PassagemJaCadastradaException, VooNaoEncontradoException,
            PassageiroNaoEncontradoException, VooLotadoException, AssentoOcupadoException {

        // Verifica se o passageiro existe
        passageiroRepository.buscarPorId(passagem.getPassageiro().getId());

        // Verifica se o voo existe
        Voo voo = vooRepository.buscarPorNumero(passagem.getVoo().getNumeroVoo());

        // Verifica se o voo está lotado
        if (voo.getAssentosDisponiveis() <= 0) {
            throw new VooLotadoException("Voo lotado! Não há assentos disponíveis.");
        }

        // Verifica se o assento já está ocupado
        if (passagemRepository.assentoOcupado(passagem.getVoo().getNumeroVoo(), passagem.getAssento())) {
            throw new AssentoOcupadoException("Assento " + passagem.getAssento() + " já está ocupado.");
        }

        // Reserva o assento (modificado para evitar ambiguidade)
        if (!voo.reservarAssento(passagem.getAssento())) {
            throw new VooLotadoException("Não foi possível reservar o assento.");
        }

        // Calcula o preço final
        passagem.setPrecoPago(voo.calcularPrecoFinal());

        // Adiciona a passagem
        passagemRepository.adicionar(passagem);
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
        Passagem passagem = passagemRepository.buscarPorId(id);
        passagem.getVoo().liberarAssento(passagem.getAssento());
        passagemRepository.remover(id);
    }

    public List<String> listarAssentosDisponiveis(String numeroVoo) throws VooNaoEncontradoException {
        Voo voo = vooRepository.buscarPorNumero(numeroVoo);
        List<String> assentosDisponiveis = new ArrayList<>();
        int totalAssentos = voo.getAeronave().getTotalAssentos();

        // Simples implementação - pode ser melhorada
        for (int i = 1; i <= totalAssentos; i++) {
            String assento = "A" + i;
            if (voo.isAssentoDisponivel(assento)) {
                assentosDisponiveis.add(assento);
            }
        }

        return assentosDisponiveis;
    }
}