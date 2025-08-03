package com.controller;

import com.model.Passagem;
import com.model.Voo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListaPassagensController {
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

    private Stage stage;

    public void init(List<Passagem> passagens) {
        colVoo.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getNumeroVoo()));
        colOrigem.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getOrigem()));
        colDestino.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getDestino()));
        colDataHora.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVoo().getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        colAssento.setCellValueFactory(new PropertyValueFactory<>("assento"));

        tablePassagens.setItems(FXCollections.observableArrayList(passagens));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void onFechar() {
        if (stage == null) {
            stage = (Stage) tablePassagens.getScene().getWindow();
        }
        stage.close();
    }
}