package com.repository;

import com.model.Passagem;
import com.exceptions.PassagemJaCadastradaException;
import com.exceptions.PassagemNaoEncontradaException;
import java.util.ArrayList;
import java.util.List;

public class PassagemRepository implements IPassagemRepository {

    private final List<Passagem> passagens = new ArrayList<>();

    @Override
    public void adicionar(Passagem passagem) throws PassagemJaCadastradaException {
        for (Passagem p : passagens) {
            if (p.getId() == passagem.getId()) {
                throw new PassagemJaCadastradaException("Passagem com este ID já cadastrada.");
            }
        }
        passagens.add(passagem);
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
        boolean removido = passagens.removeIf(p -> p.getId() == id);
        if (!removido) {
            throw new PassagemNaoEncontradaException("Passagem para remoção não encontrada.");
        }
    }
}