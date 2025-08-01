package com.repository;

import com.exceptions.EmpresaAereaJaCadastradaException;
import com.exceptions.EmpresaAereaNaoEncontradaException;
import com.model.EmpresaAerea;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmpresaAereaRepository implements IEmpresaAereaRepository {
    private static EmpresaAereaRepository instance;
    private final List<EmpresaAerea> empresas;

    private EmpresaAereaRepository() {
        this.empresas = new ArrayList<>();
    }

    public static synchronized EmpresaAereaRepository getInstance() {
        if (instance == null) {
            instance = new EmpresaAereaRepository();
        }
        return instance;
    }

    @Override
    public void cadastrar(EmpresaAerea empresa) throws EmpresaAereaJaCadastradaException {
        for (EmpresaAerea e : empresas) {
            if (e.getCnpj().equals(empresa.getCnpj())) {
                throw new EmpresaAereaJaCadastradaException("Empresa já cadastrada com este CNPJ.");
            }
        }
        empresas.add(empresa);
    }

    @Override
    public EmpresaAerea buscarPorId(int id) throws EmpresaAereaNaoEncontradaException {
        for (EmpresaAerea e : empresas) {
            if (e.getId() == id) {
                return e;
            }
        }
        throw new EmpresaAereaNaoEncontradaException("Empresa não encontrada com este ID.");
    }

    @Override
    public EmpresaAerea buscarPorCnpj(String cnpj) throws EmpresaAereaNaoEncontradaException {
        for (EmpresaAerea e : empresas) {
            if (e.getCnpj().equals(cnpj)) {
                return e;
            }
        }
        throw new EmpresaAereaNaoEncontradaException("Empresa não encontrada com este CNPJ.");
    }

    @Override
    public List<EmpresaAerea> listarTodos() { // Corrigido para listarTodos (ao invés de listarTodas)
        return new ArrayList<>(empresas);
    }

    @Override
    public boolean testarLogin(String login, String senha) {
        for (EmpresaAerea e : empresas) {
            if (e.getLogin().equals(login) && e.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remover(int id) throws EmpresaAereaNaoEncontradaException {
        Iterator<EmpresaAerea> iterator = empresas.iterator();
        boolean removido = false;

        while (iterator.hasNext()) {
            EmpresaAerea e = iterator.next();
            if (e.getId() == id) {
                iterator.remove();
                removido = true;
                break; // Sai do loop após remover
            }
        }

        if (!removido) {
            throw new EmpresaAereaNaoEncontradaException("Empresa não encontrada para remoção.");
        }
    }

    @Override
    public EmpresaAerea buscarPorLogin(String login) throws EmpresaAereaNaoEncontradaException {
        for (EmpresaAerea e : empresas) {
            if (e.getLogin().equals(login)) {
                return e;
            }
        }
        throw new EmpresaAereaNaoEncontradaException("Empresa com este login não encontrada.");
    }

    @Override
    public void atualizar(EmpresaAerea empresa) throws EmpresaAereaNaoEncontradaException {
        EmpresaAerea existente = buscarPorId(empresa.getId());
        existente.setNome(empresa.getNome());
        existente.setCnpj(empresa.getCnpj());
        existente.setLogin(empresa.getLogin());
        existente.setSenha(empresa.getSenha());
    }
}