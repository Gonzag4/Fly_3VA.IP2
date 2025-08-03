package com.controller;

import com.exceptions.EmpresaAereaJaCadastradaException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CadastroEmpresaController {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCnpj;
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
        String cnpj = txtCnpj.getText();
        String login = txtLogin.getText();
        String senha = txtSenha.getText();

        if (nome.isEmpty() || cnpj.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            lblErro.setText("Preencha todos os campos.");
            return;
        }

        try {
            SistemaVendasPassagens.getInstance().cadastrarEmpresa(nome, cnpj, login, senha);
            lblErro.setText("Cadastro realizado com sucesso!");
            stage.close();
        } catch (EmpresaAereaJaCadastradaException e) {
            lblErro.setText("Erro no cadastro: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        stage.close();
    }
}