package com.controller;

import com.exceptions.*;
import com.model.*;
import com.repository.*;
import java.time.LocalDateTime;
import java.util.List;

public class VooController {
    private static VooController instance;
    private final IVooRepository vooRepository;
    private final IAeronaveRepository aeronaveRepository;

    private VooController() {
        this.vooRepository = VooRepository.getInstance();
        this.aeronaveRepository = AeronaveRepository.getInstance();
    }

    public static synchronized VooController getInstance() {
        if (instance == null) {
            instance = new VooController();
        }
        return instance;
    }

    public void cadastrarVooNacional(String numeroVoo, String origem, String destino,
                                     LocalDateTime dataHora, double precoBase,
                                     int aeronaveId, double taxaEmbarque, int idEmpresa)
            throws VooJaCadastradoException, AeronaveNaoEncontradaException, AeronaveNaoEncontradaException {

        Aeronave aeronave = aeronaveRepository.buscarPorId(aeronaveId);
        if (aeronave.getIdEmpresa() != idEmpresa) {
            throw new AeronaveNaoEncontradaException("Aeronave não pertence à empresa");
        }

        Voo voo = new VooNacional(numeroVoo, origem, destino, dataHora,
                precoBase, aeronave.getTotalAssentos(),
                taxaEmbarque, aeronave);
        vooRepository.adicionar(voo);
    }

    public void cadastrarVooInternacional(String numeroVoo, String origem, String destino,
                                          LocalDateTime dataHora, double precoBase,
                                          int aeronaveId, double taxaEmbarque,
                                          double taxaAlfandega, int idEmpresa)
            throws VooJaCadastradoException, AeronaveNaoEncontradaException, AeronaveNaoEncontradaException {

        Aeronave aeronave = aeronaveRepository.buscarPorId(aeronaveId);
        if (aeronave.getIdEmpresa() != idEmpresa) {
            throw new AeronaveNaoEncontradaException("Aeronave não pertence à empresa");
        }

        Voo voo = new VooInternacional(numeroVoo, origem, destino, dataHora,
                precoBase, aeronave.getTotalAssentos(),
                taxaEmbarque, taxaAlfandega, aeronave);
        vooRepository.adicionar(voo);
    }

    public Voo buscarVoo(String numeroVoo) throws VooNaoEncontradoException {
        return vooRepository.buscarPorNumero(numeroVoo);
    }

    public List<Voo> listarTodosVoos() {
        return vooRepository.listarTodos();
    }

    public void removerVoo(String numeroVoo) throws VooNaoEncontradoException {
        vooRepository.remover(numeroVoo);
        System.out.println("-> Sucesso: Voo removido com sucesso");
    }

    public double calcularPrecoFinalVoo(String numeroVoo) throws VooNaoEncontradoException {
        Voo voo = vooRepository.buscarPorNumero(numeroVoo);
        return voo.calcularPrecoFinal();
    }

    public void atualizar(Voo voo) throws VooNaoEncontradoException {
        vooRepository.atualizar(voo);
    }
}