package com.controller;

import com.model.Passageiro;
import com.model.Voo;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.List;

public class ComprarPassagemController {
    private MainController mainController;
    private Stage stage;
    private Passageiro passageiro;

    @FXML
    private ComboBox<Voo> cbVoos;
    @FXML
    private ComboBox<String> cbAssentos;
    @FXML
    private Label lblMensagem;

    public void init(MainController mainController, Stage stage, Passageiro passageiro) {
        this.mainController = mainController;
        this.stage = stage;
        this.passageiro = passageiro;

        List<Voo> voos = SistemaVendasPassagens.getInstance().listarVoosDisponiveis();
        cbVoos.setItems(FXCollections.observableArrayList(voos));
        cbVoos.setOnAction(e -> carregarAssentos());
    }

    private void carregarAssentos() {
        cbAssentos.getItems().clear();
        Voo voo = cbVoos.getValue();
        if (voo != null) {
            try {
                List<String> assentos = SistemaVendasPassagens.getInstance()
                        .listarAssentosDisponiveis(voo.getNumeroVoo());
                cbAssentos.setItems(FXCollections.observableArrayList(assentos));
            } catch (Exception e) {
                lblMensagem.setText("Erro ao carregar assentos.");
            }
        }
    }

    @FXML
    private void onComprar() {
        Voo voo = cbVoos.getValue();
        String assento = cbAssentos.getValue();
        if (voo == null || assento == null) {
            lblMensagem.setText("Selecione voo e assento.");
            return;
        }
        try {
            SistemaVendasPassagens.getInstance().comprarPassagem(
                    passageiro.getId(), voo.getNumeroVoo(), assento
            );
            lblMensagem.setStyle("-fx-text-fill: green;");
            lblMensagem.setText("Passagem comprada com sucesso!");
            carregarAssentos();
        } catch (Exception e) {
            lblMensagem.setStyle("-fx-text-fill: red;");
            lblMensagem.setText("Erro: " + e.getMessage());
        }
    }

    @FXML
    private void onFechar() {
        stage.close();
    }
}