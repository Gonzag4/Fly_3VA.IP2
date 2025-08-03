package com.controller;

import com.model.Passageiro;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AtualizarPassageiroController {
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
    private Label lblMensagem;

    private Passageiro passageiro;
    private Stage stage;

    public void init(Passageiro passageiro, Stage stage) {
        this.passageiro = passageiro;
        this.stage = stage;
        txtNome.setText(passageiro.getNome());
        txtCpf.setText(passageiro.getCpf());
        txtTelefone.setText(passageiro.getTelefone());
        txtLogin.setText(passageiro.getLogin());
    }

    @FXML
    private void onSalvar() {
        try {
            passageiro.setNome(txtNome.getText());
            passageiro.setTelefone(txtTelefone.getText());
            if (!txtSenha.getText().isEmpty()) {
                passageiro.setSenha(txtSenha.getText());
            }
            SistemaVendasPassagens.getInstance().atualizarPassageiro(passageiro);
            lblMensagem.setText("Dados atualizados com sucesso.");
        } catch (Exception e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        stage.close();
    }
}