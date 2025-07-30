package com.repository;

import com.exceptions.PassageiroJaCadastradoException;
import com.exceptions.PassageiroNaoEncontradoException;
import com.model.Passageiro;
import java.util.ArrayList;
import java.util.List;

public class PassageiroRepository implements IPassageiroRepository {

    // Requisito 9: Singleton - instância única
    private static PassageiroRepository instance;
    private final List<Passageiro> passageiros;

    // Construtor PRIVADO (impede criação externa)
    private PassageiroRepository() {
        this.passageiros = new ArrayList<>();
    }

    // Requisito 9: Método estático para obter a instância (thread-safe)
    public static synchronized PassageiroRepository getInstance() {
        if (instance == null) {
            instance = new PassageiroRepository();
        }
        return instance;
    }

    @Override
    public void cadastrar(Passageiro passageiro) throws PassageiroJaCadastradoException {
        // Verifica se já existe um passageiro com o mesmo CPF
        for (Passageiro p : passageiros) {
            if (p.getCpf().equals(passageiro.getCpf())) {
                throw new PassageiroJaCadastradoException("Passageiro já cadastrado com este CPF.");
            }
        }
        passageiros.add(passageiro);
    }

    @Override
    public Passageiro buscarPorId(int id) throws PassageiroNaoEncontradoException {
        // for-each usado para percorrer as list
        for (Passageiro p : passageiros) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new PassageiroNaoEncontradoException("Passageiro com este ID não encontrado.");
    }

    @Override
    public Passageiro buscarPorCpf(String cpf) throws PassageiroNaoEncontradoException {
        // for-each usado para percorrer as list
        for (Passageiro p : passageiros) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        throw new PassageiroNaoEncontradoException("Passageiro com este CPF não encontrado.");
    }

    @Override
    public List<Passageiro> listarTodos() {
        return new ArrayList<>(passageiros); // Retorna cópia para evitar modificação externa
    }

    @Override
    public void atualizar(Passageiro passageiro) throws PassageiroNaoEncontradoException {
        Passageiro existente = buscarPorId(passageiro.getId());
        existente.setNome(passageiro.getNome());
        existente.setTelefone(passageiro.getTelefone());
    }

    @Override
    public void remover(int id) throws PassageiroNaoEncontradoException {
        boolean removido = false;
        for (int i = 0; i < passageiros.size(); i++) {
            if (passageiros.get(i).getId() == id) {
                passageiros.remove(i);
                removido = true;
                break;
            }
        }
        if (!removido) {
            throw new PassageiroNaoEncontradoException("Passageiro não encontrado para remoção.");
        }
    }
}