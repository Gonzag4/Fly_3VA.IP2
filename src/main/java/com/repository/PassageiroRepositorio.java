package com.repository;

import com.exceptions.PassageiroJaCadastradoException;
import com.exceptions.PassageiroNaoEncontradoException;
import com.model.Passageiro;

import java.util.ArrayList;
import java.util.List;

public class PassageiroRepositorio implements IPassageiroRepository {

    private final List<Passageiro> passageiros = new ArrayList<>();

    @Override
    public void cadastrar(Passageiro passageiro) throws PassageiroJaCadastradoException {
        try {
            buscarPorCpf(passageiro.getCpf());
            // Se não lançar exceção, já existe
            throw new PassageiroJaCadastradoException("Passageiro com este CPF já cadastrado.");
        } catch (PassageiroNaoEncontradoException e) {
            // Não existe, pode cadastrar
            passageiros.add(passageiro);
        }
    }

    @Override
    public Passageiro buscarPorId(int id) throws PassageiroNaoEncontradoException {
        for (Passageiro p : passageiros) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new PassageiroNaoEncontradoException("Passageiro com este ID não encontrado.");
    }

    @Override
    public Passageiro buscarPorCpf(String cpf) throws PassageiroNaoEncontradoException {
        for (Passageiro p : passageiros) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        throw new PassageiroNaoEncontradoException("Passageiro com este CPF não encontrado.");
    }

    @Override
    public List<Passageiro> listarTodos() {
        return new ArrayList<>(passageiros);
    }

    @Override
    public void atualizar(Passageiro passageiro) throws PassageiroNaoEncontradoException {
        for (int i = 0; i < passageiros.size(); i++) {
            if (passageiros.get(i).getId() == passageiro.getId()) {
                passageiros.set(i, passageiro);
                return;
            }
        }
        throw new PassageiroNaoEncontradoException("Passageiro para atualização não encontrado.");
    }

    @Override
    public void remover(int id) throws PassageiroNaoEncontradoException {
        boolean removido = passageiros.removeIf(p -> p.getId() == id);
        if (!removido) {
            throw new PassageiroNaoEncontradoException("Passageiro para remoção não encontrado.");
        }
    }
}