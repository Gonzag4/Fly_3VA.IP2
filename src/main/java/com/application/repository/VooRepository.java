package com.application.repository;

import com.application.model.Voo;
import com.application.exceptions.VooJaCadastradoException;
import com.application.exceptions.VooNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class VooRepository implements IVooRepository {

    private final List<Voo> voos = new ArrayList<>();

    @Override
    public void adicionar(Voo voo) throws VooJaCadastradoException {
        for (Voo v : voos) {
            if (v.getNumeroVoo().equals(voo.getNumeroVoo())) {
                throw new VooJaCadastradoException("Voo com este número já cadastrado.");
            }
        }
        voos.add(voo);
    }

    @Override
    public Voo buscarPorNumero(String numeroVoo) throws VooNaoEncontradoException {
        for (Voo v : voos) {
            if (v.getNumeroVoo().equals(numeroVoo)) {
                return v;
            }
        }
        throw new VooNaoEncontradoException("Voo com este número não encontrado.");
    }

    @Override
    public List<Voo> listarTodos() {
        return new ArrayList<>(voos);
    }

    @Override
    public void atualizar(Voo voo) throws VooNaoEncontradoException {
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getNumeroVoo().equals(voo.getNumeroVoo())) {
                voos.set(i, voo);
                return;
            }
        }
        throw new VooNaoEncontradoException("Voo para atualização não encontrado.");
    }

    @Override
    public void remover(String numeroVoo) throws VooNaoEncontradoException {
        boolean removido = voos.removeIf(v -> v.getNumeroVoo().equals(numeroVoo));
        if (!removido) {
            throw new VooNaoEncontradoException("Voo para remoção não encontrado.");
        }
    }
}