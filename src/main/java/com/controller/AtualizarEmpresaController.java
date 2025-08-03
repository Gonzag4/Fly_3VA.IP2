package com.controller;

import com.model.EmpresaAerea;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class AtualizarEmpresaController {
    private Stage stage;
    private EmpresaAerea empresa;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCnpj;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblMensagem;

    public void init(Stage stage, EmpresaAerea empresa) {
        this.stage = stage;
        this.empresa = empresa;
        txtNome.setText(empresa.getNome());
        txtCnpj.setText(empresa.getCnpj());
        txtLogin.setText(empresa.getLogin());
        txtSenha.setText(empresa.getSenha());
    }

    @FXML
    private void onSalvar() {
        try {
            empresa.setNome(txtNome.getText());
            empresa.setCnpj(txtCnpj.getText());
            empresa.setLogin(txtLogin.getText());
            empresa.setSenha(txtSenha.getText());

            SistemaVendasPassagens.getInstance().atualizarEmpresa(empresa);
            lblMensagem.setText("Dados atualizados com sucesso!");
        } catch (Exception e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        if (stage != null) stage.close();
    }
}