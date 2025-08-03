package com.controller;

import com.model.Aeronave;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

public class CadastrarVooNacionalController {
    private Stage stage;
    private int idEmpresa;

    @FXML
    private TextField txtNumeroVoo;
    @FXML
    private TextField txtOrigem;
    @FXML
    private TextField txtDestino;
    @FXML
    private TextField txtPrecoBase;
    @FXML
    private TextField txtAeronaveId;
    @FXML
    private TextField txtTaxaEmbarque;
    @FXML
    private Label lblMensagem;
    @FXML
    private TextField txtData;
    @FXML
    private TextField txtHora;

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public void init(Stage stage, int idEmpresa) {
        this.stage = stage;
        this.idEmpresa = idEmpresa;
    }

    @FXML
    private void onCadastrar() {
        try {
            String numeroVoo = txtNumeroVoo.getText();
            String origem = txtOrigem.getText();
            String destino = txtDestino.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(txtData.getText(), formatter);
            LocalTime hora = LocalTime.parse(txtHora.getText()); // formato HH:mm
            LocalDateTime dataHora = LocalDateTime.of(data, hora);
            double precoBase = Double.parseDouble(txtPrecoBase.getText());
            double taxaEmbarque = Double.parseDouble(txtTaxaEmbarque.getText());
            int aeronaveId = Integer.parseInt(txtAeronaveId.getText());

            com.controller.SistemaVendasPassagens.getInstance().cadastrarVooNacional(
                    numeroVoo, origem, destino, dataHora, precoBase, aeronaveId, taxaEmbarque, idEmpresa
            );

            lblMensagem.setText("Voo nacional cadastrado com sucesso!");
        } catch (Exception e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        if (stage != null) stage.close();
    }
}