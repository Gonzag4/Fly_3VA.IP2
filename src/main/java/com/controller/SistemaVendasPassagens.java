package com.controller;

import com.exceptions.*;
import com.model.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Fachada completa do sistema de vendas de passagens aéreas
 * Implementa o padrão Facade para fornecer uma interface única e simplificada
 * para integração com a camada de view (JavaFX)
 */
public class SistemaVendasPassagens {
    private static SistemaVendasPassagens instance;
    private final PassageiroController passageiroController;
    private final VooController vooController;
    private final PassagemController passagemController;
    private final AeronaveController aeronaveController;
    private final EmpresaAereaController empresaController;

    private SistemaVendasPassagens() {
        this.passageiroController = PassageiroController.getInstance();
        this.vooController = VooController.getInstance();
        this.passagemController = PassagemController.getInstance();
        this.aeronaveController = AeronaveController.getInstance();
        this.empresaController = EmpresaAereaController.getInstance();
    }

    public static synchronized SistemaVendasPassagens getInstance() {
        if (instance == null) {
            instance = new SistemaVendasPassagens();
        }
        return instance;
    }

    // ========== MÉTODOS DE AUTENTICAÇÃO ==========

    public Passageiro loginPassageiro(String login, String senha) throws PassageiroNaoEncontradoException {
        try {
            if (passageiroController.testarLogin(login, senha)) {
                return passageiroController.buscarPorLogin(login);
            }
        } catch (Exception e) {
            throw new PassageiroNaoEncontradoException("Erro durante o login: " + e.getMessage());
        }
        throw new PassageiroNaoEncontradoException("Login ou senha inválidos");
    }

    public EmpresaAerea loginEmpresa(String login, String senha) throws EmpresaAereaNaoEncontradaException {
        try {
            if (empresaController.testarLogin(login, senha)) {
                return empresaController.buscarPorLogin(login);
            }
        } catch (Exception e) {
            throw new EmpresaAereaNaoEncontradaException("Erro durante o login: " + e.getMessage());
        }
        throw new EmpresaAereaNaoEncontradaException("Login ou senha inválidos");
    }

    // ========== MÉTODOS DE PASSAGEIRO ==========

    public void cadastrarPassageiro(String nome, String cpf, String telefone, String login, String senha)
            throws PassageiroJaCadastradoException {
        try {
            passageiroController.cadastrarPassageiro(nome, cpf, telefone, login, senha);
        } catch (Exception e) {
            throw new PassageiroJaCadastradoException("Erro ao cadastrar passageiro: " + e.getMessage());
        }
    }

    public Passageiro buscarPassageiro(int id) throws PassageiroNaoEncontradoException {
        try {
            return passageiroController.buscarPassageiroPorId(id);
        } catch (Exception e) {
            throw new PassageiroNaoEncontradoException("Erro ao buscar passageiro: " + e.getMessage());
        }
    }

    public Passageiro buscarPassageiroPorCpf(String cpf) throws PassageiroNaoEncontradoException {
        try {
            return passageiroController.buscarPassageiroPorCpf(cpf);
        } catch (Exception e) {
            throw new PassageiroNaoEncontradoException("Erro ao buscar passageiro: " + e.getMessage());
        }
    }

    public Passageiro buscarPassageiroPorLogin(String login) throws PassageiroNaoEncontradoException {
        try {
            return passageiroController.buscarPorLogin(login);
        } catch (Exception e) {
            throw new PassageiroNaoEncontradoException("Erro ao buscar passageiro: " + e.getMessage());
        }
    }

    public List<Passageiro> listarPassageiros() {
        return passageiroController.listarTodosPassageiros();
    }

    public void atualizarPassageiro(Passageiro passageiro) throws PassageiroNaoEncontradoException {
        try {
            passageiroController.atualizarPassageiro(passageiro);
        } catch (Exception e) {
            throw new PassageiroNaoEncontradoException("Erro ao atualizar passageiro: " + e.getMessage());
        }
    }

    // ========== MÉTODOS DE EMPRESA AÉREA ==========

    public void cadastrarEmpresa(String nome, String cnpj, String login, String senha)
            throws EmpresaAereaJaCadastradaException {
        try {
            empresaController.cadastrarEmpresa(nome, cnpj, login, senha);
        } catch (Exception e) {
            throw new EmpresaAereaJaCadastradaException("Erro ao cadastrar empresa: " + e.getMessage());
        }
    }

    public void atualizarEmpresa(EmpresaAerea empresa) throws EmpresaAereaNaoEncontradaException {
        try {
            empresaController.atualizarEmpresa(empresa);
        } catch (Exception e) {
            throw new EmpresaAereaNaoEncontradaException("Erro ao atualizar empresa: " + e.getMessage());
        }
    }

    public EmpresaAerea buscarEmpresa(int id) throws EmpresaAereaNaoEncontradaException {
        try {
            return empresaController.buscarEmpresaPorId(id);
        } catch (Exception e) {
            throw new EmpresaAereaNaoEncontradaException("Erro ao buscar empresa: " + e.getMessage());
        }
    }

    public List<EmpresaAerea> listarEmpresas() {
        return empresaController.listarTodasEmpresas();
    }

    // ========== MÉTODOS DE AERONAVE ==========

    public void cadastrarAeronave(String modelo, int fileiras, int assentosPorFileira, int idEmpresa)
            throws AeronaveJaCadastradaException {
        try {
            aeronaveController.cadastrarAeronave(modelo, fileiras, assentosPorFileira, idEmpresa);
        } catch (Exception e) {
            throw new AeronaveJaCadastradaException("Erro ao cadastrar aeronave: " + e.getMessage());
        }
    }

    public Aeronave buscarAeronave(int id) throws AeronaveNaoEncontradaException {
        try {
            return aeronaveController.buscarAeronavePorId(id);
        } catch (Exception e) {
            throw new AeronaveNaoEncontradaException("Erro ao buscar aeronave: " + e.getMessage());
        }
    }

    public List<Aeronave> listarAeronaves() {
        return aeronaveController.listarTodasAeronaves();
    }

    // ========== MÉTODOS DE VOO ==========

    public void cadastrarVooNacional(String numeroVoo, String origem, String destino,
                                     LocalDateTime dataHora, double precoBase,
                                     int aeronaveId, double taxaEmbarque, int idEmpresa)
            throws VooJaCadastradoException, AeronaveNaoEncontradaException {
        try {
            vooController.cadastrarVooNacional(numeroVoo, origem, destino, dataHora,
                    precoBase, aeronaveId, taxaEmbarque, idEmpresa);
        } catch (AeronaveNaoEncontradaException e) {
            throw e;
        } catch (Exception e) {
            throw new VooJaCadastradoException("Erro ao cadastrar voo nacional: " + e.getMessage());
        }
    }

    public void cadastrarVooInternacional(String numeroVoo, String origem, String destino,
                                          LocalDateTime dataHora, double precoBase,
                                          int aeronaveId, double taxaEmbarque,
                                          double taxaAlfandega, int idEmpresa)
            throws VooJaCadastradoException, AeronaveNaoEncontradaException {
        try {
            vooController.cadastrarVooInternacional(numeroVoo, origem, destino, dataHora,
                    precoBase, aeronaveId, taxaEmbarque, taxaAlfandega, idEmpresa);
        } catch (AeronaveNaoEncontradaException e) {
            throw e;
        } catch (Exception e) {
            throw new VooJaCadastradoException("Erro ao cadastrar voo internacional: " + e.getMessage());
        }
    }

    public Voo buscarVoo(String numeroVoo) throws VooNaoEncontradoException {
        try {
            return vooController.buscarVoo(numeroVoo);
        } catch (Exception e) {
            throw new VooNaoEncontradoException("Erro ao buscar voo: " + e.getMessage());
        }
    }

    public List<Voo> listarVoos() {
        return vooController.listarTodosVoos();
    }

    public List<Voo> listarVoosDisponiveis() {
        try {
            return vooController.listarTodosVoos().stream()
                    .filter(v -> v.getAssentosDisponiveis() > 0)
                    .toList();
        } catch (Exception e) {
            System.err.println("Erro ao listar voos disponíveis: " + e.getMessage());
            return List.of();
        }
    }

    public double calcularPrecoVoo(String numeroVoo) throws VooNaoEncontradoException {
        try {
            return vooController.calcularPrecoFinalVoo(numeroVoo);
        } catch (Exception e) {
            throw new VooNaoEncontradoException("Erro ao calcular preço do voo: " + e.getMessage());
        }
    }

    // ========== MÉTODOS DE PASSAGEM ==========

    public void comprarPassagem(int idPassageiro, String numeroVoo, String assento)
            throws PassagemJaCadastradaException, VooNaoEncontradoException,
            PassageiroNaoEncontradoException, VooLotadoException, AssentoOcupadoException {
        try {
            Passageiro passageiro = passageiroController.buscarPassageiroPorId(idPassageiro);
            Voo voo = vooController.buscarVoo(numeroVoo);

            Passagem passagem = new Passagem(passageiro, voo, assento);
            passagemController.comprarPassagem(passagem);
        } catch (PassageiroNaoEncontradoException | VooNaoEncontradoException |
                 VooLotadoException | AssentoOcupadoException e) {
            throw e;
        } catch (Exception e) {
            throw new PassagemJaCadastradaException("Erro ao comprar passagem: " + e.getMessage());
        }
    }

    public Passagem buscarPassagem(int id) throws PassagemNaoEncontradaException {
        try {
            return passagemController.buscarPassagemPorId(id);
        } catch (Exception e) {
            throw new PassagemNaoEncontradaException("Erro ao buscar passagem: " + e.getMessage());
        }
    }

    public List<Passagem> listarPassagens() {
        return passagemController.listarTodasPassagens();
    }

    public List<Passagem> buscarPassagensPorPassageiro(int idPassageiro)
            throws PassageiroNaoEncontradoException {
        try {
            return passagemController.buscarPassagensPorPassageiro(idPassageiro);
        } catch (Exception e) {
            throw new PassageiroNaoEncontradoException("Erro ao buscar passagens: " + e.getMessage());
        }
    }

    public List<String> listarAssentosDisponiveis(String numeroVoo) throws VooNaoEncontradoException {
        try {
            return passagemController.listarAssentosDisponiveis(numeroVoo);
        } catch (VooNaoEncontradoException e) {
            throw e;
        } catch (Exception e) {
            throw new VooNaoEncontradoException("Erro ao listar assentos: " + e.getMessage());
        }
    }

    public void cancelarPassagem(int idPassagem) throws PassagemNaoEncontradaException {
        try {
            passagemController.removerPassagem(idPassagem);
        } catch (Exception e) {
            throw new PassagemNaoEncontradaException("Erro ao cancelar passagem: " + e.getMessage());
        }
    }
}