package com.repository;

import com.model.Voo;
import com.exceptions.VooJaCadastradoException;
import com.exceptions.VooNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;

public class VooRepository implements IVooRepository {
    // Requisito 9: Singleton
    private static VooRepository instance;
    private final List<Voo> voos;

    private VooRepository() {
        this.voos = new ArrayList<>();
    }

    public static synchronized VooRepository getInstance() {
        if (instance == null) {
            instance = new VooRepository();
        }
        return instance;
    }

    @Override
    public void adicionar(Voo voo) throws VooJaCadastradoException {
        // Verifica se já existe um voo com o mesmo número
        for (Voo v : voos) {
            if (v.getNumeroVoo().equals(voo.getNumeroVoo())) {
                throw new VooJaCadastradoException("Voo já cadastrado com este número.");
            }
        }
        voos.add(voo);
    }

    @Override
    public Voo buscarPorNumero(String numeroVoo) throws VooNaoEncontradoException {
        // for-each usado para percorrer as list
        for (Voo v : voos) {
            if (v.getNumeroVoo().equals(numeroVoo)) {
                return v;
            }
        }
        throw new VooNaoEncontradoException("Voo com este número não encontrado.");
    }

    @Override
    public List<Voo> listarTodos() {
        return new ArrayList<>(voos); // Retorna cópia para evitar modificação externa
    }

    @Override
    public void atualizar(Voo voo) throws VooNaoEncontradoException {
        // for-each usado para percorrer as list
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getNumeroVoo().equals(voo.getNumeroVoo())) {
                voos.set(i, voo);
                return;
            }
        }
        throw new VooNaoEncontradoException("Voo não encontrado para atualização.");
    }

    @Override
    public void remover(String numeroVoo) throws VooNaoEncontradoException {
        boolean removido = false;
        for (int i = 0; i < voos.size(); i++) {
            if (voos.get(i).getNumeroVoo().equals(numeroVoo)) {
                voos.remove(i);
                removido = true;
                break;
            }
        }
        if (!removido) {
            throw new VooNaoEncontradoException("Voo não encontrado para remoção.");
        }
    }
}