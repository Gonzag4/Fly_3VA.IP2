package com.controller;

import com.exceptions.PassageiroJaCadastradoException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CadastroPassageiroController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblErro;

    private MainController mainController;
    private Stage stage;

    public void init(MainController mainController, Stage stage) {
        this.mainController = mainController;
        this.stage = stage;
    }

    @FXML
    private void onCadastrar() {
        String nome = txtNome.getText();
        String cpf = txtCpf.getText();
        String telefone = txtTelefone.getText();
        String login = txtLogin.getText();
        String senha = txtSenha.getText();

        if (nome.isEmpty() || cpf.isEmpty() || telefone.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            lblErro.setText("Preencha todos os campos.");
            return;
        }

        try {
            SistemaVendasPassagens.getInstance().cadastrarPassageiro(nome, cpf, telefone, login, senha);
            lblErro.setText("Cadastro realizado com sucesso!");
            stage.close();
        } catch (PassageiroJaCadastradoException e) {
            lblErro.setText("Erro no cadastro: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        stage.close();
    }
}