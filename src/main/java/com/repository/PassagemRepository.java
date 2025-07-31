package com.repository;

import com.exceptions.VooNaoEncontradoException;
import com.model.Passagem;
import com.exceptions.PassagemJaCadastradaException;
import com.exceptions.PassagemNaoEncontradaException;
import java.util.ArrayList;
import java.util.List;

public class PassagemRepository implements IPassagemRepository {
    // Requisito 9: Singleton
    private static PassagemRepository instance;
    private final List<Passagem> passagens;

    private PassagemRepository() {
        this.passagens = new ArrayList<>();
    }

    public static synchronized PassagemRepository getInstance() {
        if (instance == null) {
            instance = new PassagemRepository();
        }
        return instance;
    }

    @Override
    public void adicionar(Passagem passagem) throws PassagemJaCadastradaException {
        // Verifica se já existe uma passagem com o mesmo ID
        for (Passagem p : passagens) {
            if (p.getId() == passagem.getId()) {
                throw new PassagemJaCadastradaException("Passagem já cadastrada com este ID.");
            }
        }
        passagens.add(passagem);
    }

    @Override
    public boolean assentoOcupado(String numeroVoo, String assento) {
        return passagens.stream()
                .anyMatch(p -> p.getVoo().getNumeroVoo().equals(numeroVoo)
                        && p.getAssento().equalsIgnoreCase(assento));
    }

    @Override
    public Passagem buscarPorId(int id) throws PassagemNaoEncontradaException {
        for (Passagem p : passagens) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new PassagemNaoEncontradaException("Passagem com este ID não encontrada.");
    }

    @Override
    public List<Passagem> listarTodas() {
        return new ArrayList<>(passagens);
    }

    @Override
    public List<Passagem> buscarPorPassageiro(int idPassageiro) {
        List<Passagem> resultado = new ArrayList<>();
        for (Passagem p : passagens) {
            if (p.getPassageiro().getId() == idPassageiro) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public void remover(int id) throws PassagemNaoEncontradaException {
        boolean removido = false;
        for (int i = 0; i < passagens.size(); i++) {
            if (passagens.get(i).getId() == id) {
                passagens.remove(i);
                removido = true;
                break;
            }
        }
        if (!removido) {
            throw new PassagemNaoEncontradaException("Passagem não encontrada para remoção.");
        }
    }
}