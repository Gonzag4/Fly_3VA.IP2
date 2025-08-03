package com.controller;

import com.model.Aeronave;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.List;
import java.util.stream.Collectors;

public class ListaAeronavesController {
    @FXML
    private TableView<Aeronave> tableAeronaves;
    @FXML
    private TableColumn<Aeronave, Integer> colId;
    @FXML
    private TableColumn<Aeronave, String> colModelo;
    @FXML
    private TableColumn<Aeronave, Integer> colFileiras;
    @FXML
    private TableColumn<Aeronave, Integer> colAssentos;

    private Stage stage;

    public void init(List<Aeronave> aeronaves, int idEmpresa, Stage stage) {
        this.stage = stage;
        // Filtra aeronaves da empresa
        List<Aeronave> aeronavesEmpresa = aeronaves.stream()
                .filter(a -> a.getIdEmpresa() == idEmpresa)
                .collect(Collectors.toList());

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        colModelo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getModelo()));
        colFileiras.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getFileiras()).asObject());
        colAssentos.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAssentosPorFileira()).asObject());
        tableAeronaves.setItems(FXCollections.observableArrayList(aeronavesEmpresa));
    }

    @FXML
    private void onFechar() {
        if (stage != null) stage.close();
    }
}