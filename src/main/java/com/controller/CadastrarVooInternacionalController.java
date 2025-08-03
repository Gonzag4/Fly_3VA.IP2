package com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import com.model.EmpresaAerea;

public class CadastrarVooInternacionalController {
    private Stage stage;
    private int idEmpresa;

    @FXML
    private TextField txtNumeroVoo;
    @FXML
    private TextField txtOrigem;
    @FXML
    private TextField txtDestino;
    @FXML
    private TextField txtData;
    @FXML
    private TextField txtHora;
    @FXML
    private TextField txtPrecoBase;
    @FXML
    private TextField txtAeronaveId;
    @FXML
    private TextField txtTaxaEmbarque;
    @FXML
    private TextField txtTaxaAlfandega;
    @FXML
    private Label lblMensagem;

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
            LocalDate data = LocalDate.parse(txtData.getText()); // formato: yyyy-MM-dd
            LocalTime hora = LocalTime.parse(txtHora.getText()); // formato HH:mm
            LocalDateTime dataHora = LocalDateTime.of(data, hora);
            double precoBase = Double.parseDouble(txtPrecoBase.getText());
            int aeronaveId = Integer.parseInt(txtAeronaveId.getText());
            double taxaEmbarque = Double.parseDouble(txtTaxaEmbarque.getText());
            double taxaAlfandega = Double.parseDouble(txtTaxaAlfandega.getText());

            SistemaVendasPassagens.getInstance().cadastrarVooInternacional(
                    numeroVoo, origem, destino, dataHora, precoBase, aeronaveId, taxaEmbarque, taxaAlfandega, idEmpresa
            );

            lblMensagem.setText("Voo internacional cadastrado com sucesso!");
        } catch (Exception e) {
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        if (stage != null) stage.close();
    }
}