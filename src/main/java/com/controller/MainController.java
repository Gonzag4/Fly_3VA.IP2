package com.controller;

import com.view.MainView;
import com.model.EmpresaAerea;
import com.model.Aeronave;
import com.exceptions.EmpresaAereaJaCadastradaException;
import com.exceptions.EmpresaAereaNaoEncontradaException;
import com.model.Passageiro;
import com.exceptions.PassageiroNaoEncontradoException;
import com.exceptions.PassageiroJaCadastradoException;
import java.util.List;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController {
    private final Stage stage;
    private final MainView view;
    private final SistemaVendasPassagens sistema;

    public MainController(Stage stage) {
        this.stage = stage;
        this.view = new MainView(this, stage);
        this.sistema = SistemaVendasPassagens.getInstance();
    }

    public void mostrarTelaPrincipal() {
        view.telaPrincipal();
    }

    public void mostrarTelaLoginPassageiro() {
        view.telaLoginPassageiro();
    }

    public void mostrarTelaCadastroPassageiro() {
        view.telaCadastroPassageiro();
    }

    public void mostrarTelaLoginEmpresa() {
        view.telaLoginEmpresa();
    }

    public void mostrarTelaCadastroEmpresa() {
        view.telaCadastroEmpresa();
    }

    public void efetuarLoginPassageiro(String login, String senha, Label lblMensagem) {
        try {
            Passageiro passageiro = sistema.loginPassageiro(login, senha);
            lblMensagem.setText("Bem-vindo, " + passageiro.getNome() + "!");
            view.telaMenuPassageiro(passageiro);
        } catch (PassageiroNaoEncontradoException e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    public void efetuarCadastroPassageiro(String nome, String cpf, String telefone, String login, String senha, Label lblMensagem) {
        try {
            sistema.cadastrarPassageiro(nome, cpf, telefone, login, senha);
            lblMensagem.setText("Cadastro realizado com sucesso!");
            mostrarTelaPrincipal();
        } catch (PassageiroJaCadastradoException e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    public void efetuarCadastroEmpresa(String nome, String cnpj, String login, String senha, Label lblMensagem) {
        try {
            sistema.cadastrarEmpresa(nome, cnpj, login, senha);
            lblMensagem.setText("Empresa cadastrada com sucesso!");
            mostrarTelaPrincipal();
        } catch (EmpresaAereaJaCadastradaException e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    public void efetuarLoginEmpresa(String login, String senha, Label lblMensagem) {
        try {
            EmpresaAerea empresa = sistema.loginEmpresa(login, senha);
            lblMensagem.setText("Bem-vindo, " + empresa.getNome() + "!");
            view.telaMenuEmpresa(empresa);
        } catch (EmpresaAereaNaoEncontradaException e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    public void mostrarVoosDisponiveis(Passageiro passageiro) {
        var voos = sistema.listarVoosDisponiveis();
        view.telaListaVoos(voos, passageiro);
    }

    public void mostrarComprarPassagem(Passageiro passageiro) {
        var voos = sistema.listarVoosDisponiveis();
        view.telaComprarPassagem(voos, passageiro);
    }

    public void mostrarMinhasPassagens(Passageiro passageiro) {
        try {
            var passagens = sistema.buscarPassagensPorPassageiro(passageiro.getId());
            view.telaMinhasPassagens(passagens, passageiro);
        } catch (PassageiroNaoEncontradoException e) {
            view.mostrarMensagemErro("Erro ao buscar passagens: " + e.getMessage());
        }
    }

    public void mostrarAtualizarPassageiro(Passageiro passageiro) {
        view.telaAtualizarPassageiro(passageiro);
    }

    public List<String> listarAssentosDisponiveis(String numeroVoo) throws Exception {
        return sistema.listarAssentosDisponiveis(numeroVoo);
    }

    public void comprarPassagem(int idPassageiro, String numeroVoo, String assento) throws Exception {
        sistema.comprarPassagem(idPassageiro, numeroVoo, assento);
    }

    public void mostrarCancelarPassagem(Passageiro passageiro) {
        try {
            var passagens = sistema.buscarPassagensPorPassageiro(passageiro.getId());
            view.telaCancelarPassagem(passagens, passageiro);
        } catch (PassageiroNaoEncontradoException e) {
            view.mostrarMensagemErro("Erro ao buscar passagens: " + e.getMessage());
        }
    }

    public void cancelarPassagem(int idPassagem) throws Exception {
        sistema.cancelarPassagem(idPassagem);
    }

    public void atualizarPassageiro(com.model.Passageiro passageiro) throws Exception {
        sistema.atualizarPassageiro(passageiro);
    }

    public void mostrarCadastrarAeronave(com.model.EmpresaAerea empresa) {
        view.telaCadastrarAeronave(empresa);
    }

    public void cadastrarAeronave(String modelo, int fileiras, int assentos, com.model.EmpresaAerea empresa) throws Exception {
        sistema.cadastrarAeronave(modelo, fileiras, assentos, empresa.getId());
    }

    public void mostrarCadastrarVooNacional(com.model.EmpresaAerea empresa) {
        view.telaCadastrarVooNacional(empresa);
    }

    public List<Aeronave> listarAeronavesEmpresa(int idEmpresa) {
        return sistema.listarAeronaves().stream()
                .filter(a -> a.getIdEmpresa() == idEmpresa)
                .toList();
    }

    public void cadastrarVooNacional(String numero, String origem, String destino, java.time.LocalDateTime dataHora,
                                     double preco, int aeronaveId, double taxa, int empresaId) throws Exception {
        sistema.cadastrarVooNacional(numero, origem, destino, dataHora, preco, aeronaveId, taxa, empresaId);
    }

    public void mostrarCadastrarVooInternacional(com.model.EmpresaAerea empresa) {
        view.telaCadastrarVooInternacional(empresa);
    }

    public void cadastrarVooInternacional(String numero, String origem, String destino, java.time.LocalDateTime dataHora,
                                          double preco, int aeronaveId, double taxaEmbarque, double taxaAlfandega, int empresaId) throws Exception {
        sistema.cadastrarVooInternacional(numero, origem, destino, dataHora, preco, aeronaveId, taxaEmbarque, taxaAlfandega, empresaId);
    }

    public void mostrarListarAeronavesEmpresa(com.model.EmpresaAerea empresa) {
        var aeronaves = listarAeronavesEmpresa(empresa.getId());
        view.telaListarAeronaves(aeronaves, empresa);
    }

    public void mostrarListarVoosEmpresa(com.model.EmpresaAerea empresa) {
        var voos = sistema.listarVoos().stream()
                .filter(v -> v.getAeronave().getIdEmpresa() == empresa.getId())
                .toList();
        view.telaListarVoos(voos, empresa);
    }

    public void mostrarAtualizarEmpresa(com.model.EmpresaAerea empresa) {
        view.telaAtualizarEmpresa(empresa);
    }

    public void atualizarEmpresa(com.model.EmpresaAerea empresa) throws Exception {
        sistema.atualizarEmpresa(empresa);
    }
}