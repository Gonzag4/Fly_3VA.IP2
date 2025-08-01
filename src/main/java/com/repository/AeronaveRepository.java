package com.repository;

import com.exceptions.AeronaveJaCadastradaException;
import com.exceptions.AeronaveNaoEncontradaException;
import com.model.Aeronave;
import java.util.ArrayList;
import java.util.List;

public class AeronaveRepository implements IAeronaveRepository {
    private static AeronaveRepository instance;
    private final List<Aeronave> aeronaves;

    private AeronaveRepository() {
        this.aeronaves = new ArrayList<>();
    }

    public static synchronized AeronaveRepository getInstance() {
        if (instance == null) {
            instance = new AeronaveRepository();
        }
        return instance;
    }

    @Override
    public void cadastrar(Aeronave aeronave) throws AeronaveJaCadastradaException {
        for (Aeronave a : aeronaves) {
            if (a.getId() == aeronave.getId()) {
                throw new AeronaveJaCadastradaException("Aeronave já cadastrada com este ID.");
            }
        }
        aeronaves.add(aeronave);
    }

    @Override
    public Aeronave buscarPorId(int id) throws AeronaveNaoEncontradaException {
        for (Aeronave a : aeronaves) {
            if (a.getId() == id) {
                return a;
            }
        }
        throw new AeronaveNaoEncontradaException("Aeronave não encontrada com este ID.");
    }

    @Override
    public List<Aeronave> listarTodas() {
        return new ArrayList<>(aeronaves);
    }

    @Override
    public void remover(int id) throws AeronaveNaoEncontradaException {
        boolean removido = aeronaves.removeIf(a -> a.getId() == id);
        if (!removido) {
            throw new AeronaveNaoEncontradaException("Aeronave não encontrada para remoção.");
        }
    }
}