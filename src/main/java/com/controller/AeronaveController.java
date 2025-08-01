package com.controller;

import com.exceptions.AeronaveJaCadastradaException;
import com.exceptions.AeronaveNaoEncontradaException;
import com.model.Aeronave;
import com.repository.IAeronaveRepository;
import com.repository.AeronaveRepository;
import java.util.List;

public class AeronaveController {
    private static AeronaveController instance;
    private final IAeronaveRepository repository;

    private AeronaveController() {
        this.repository = AeronaveRepository.getInstance();
    }

    public static synchronized AeronaveController getInstance() {
        if (instance == null) {
            instance = new AeronaveController();
        }
        return instance;
    }

    public void cadastrarAeronave(String modelo, int fileiras, int assentosPorFileira)
            throws AeronaveJaCadastradaException {
        Aeronave aeronave = new Aeronave(modelo, fileiras, assentosPorFileira);
        repository.cadastrar(aeronave);
    }

    public Aeronave buscarAeronavePorId(int id) throws AeronaveNaoEncontradaException {
        return repository.buscarPorId(id);
    }

    public List<Aeronave> listarTodasAeronaves() {
        return repository.listarTodas();
    }

    public void removerAeronave(int id) throws AeronaveNaoEncontradaException {
        repository.remover(id);
    }
}