package com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import com.model.EmpresaAerea;

public class CadastrarAeronaveController {
    private Stage stage;
    private EmpresaAerea empresa;

    @FXML
    private TextField txtModelo;
    @FXML
    private TextField txtFileiras;
    @FXML
    private TextField txtAssentosPorFileira;
    @FXML
    private Label lblMensagem;

    public void init(Stage stage, EmpresaAerea empresa) {
        this.stage = stage;
        this.empresa = empresa;
    }

    @FXML
    private void onCadastrar() {
        String modelo = txtModelo.getText();
        String fileirasStr = txtFileiras.getText();
        String assentosStr = txtAssentosPorFileira.getText();

        try {
            int fileiras = Integer.parseInt(fileirasStr);
            int assentos = Integer.parseInt(assentosStr);
            int idEmpresa = empresa.getId();
            com.controller.SistemaVendasPassagens.getInstance().cadastrarAeronave(modelo, fileiras, assentos, empresa.getId());

            lblMensagem.setText("Aeronave cadastrada com sucesso!");
        } catch (Exception e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        if (stage != null) stage.close();
    }
}