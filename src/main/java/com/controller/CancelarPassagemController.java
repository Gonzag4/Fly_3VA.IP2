package com.controller;

import com.model.Passagem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class CancelarPassagemController {
    @FXML
    private TableView<Passagem> tablePassagens;
    @FXML
    private TableColumn<Passagem, String> colVoo;
    @FXML
    private TableColumn<Passagem, String> colOrigem;
    @FXML
    private TableColumn<Passagem, String> colDestino;
    @FXML
    private TableColumn<Passagem, String> colDataHora;
    @FXML
    private TableColumn<Passagem, String> colAssento;
    @FXML
    private Label lblMensagem;

    private Stage stage;
    private List<Passagem> passagens;

    public void init(List<Passagem> passagens, Stage stage) {
        this.passagens = passagens;
        this.stage = stage;

        colVoo.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getVoo().getNumeroVoo()));
        colOrigem.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getVoo().getOrigem()));
        colDestino.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getVoo().getDestino()));
        colDataHora.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getVoo().getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        colAssento.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getAssento()));

        tablePassagens.setItems(FXCollections.observableArrayList(passagens));
    }

    @FXML
    private void onCancelar() {
        Passagem selecionada = tablePassagens.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            lblMensagem.setText("Selecione uma passagem para cancelar.");
            return;
        }
        try {
            SistemaVendasPassagens.getInstance().cancelarPassagem(selecionada.getId());
            tablePassagens.getItems().remove(selecionada);
            lblMensagem.setText("Passagem cancelada com sucesso.");
        } catch (Exception e) {
            lblMensagem.setText("Erro ao cancelar: " + e.getMessage());
        }
    }

    @FXML
    private void onFechar() {
        if (stage == null) {
            stage = (Stage) tablePassagens.getScene().getWindow();
        }
        stage.close();
    }
}