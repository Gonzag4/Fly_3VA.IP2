package com.controller;

import com.model.Voo;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.util.List;
import javafx.stage.Stage;

public class ListaVoosController {
    @FXML
    private ListView<String> listVoos;

    public void init(List<Voo> voos) {
        for (Voo voo : voos) {
            listVoos.getItems().add(
                    "Voo: " + voo.getNumeroVoo() +
                            " | Origem: " + voo.getOrigem() +
                            " | Destino: " + voo.getDestino() +
                            " | Data/Hora: " + voo.getDataHora() +
                            " | Preço: R$" + String.format("%.2f", voo.calcularPrecoFinal())
            );
        }
    }

    @FXML
    private void onFechar() {
        // Fecha a janela atual
        ((Stage) listVoos.getScene().getWindow()).close();
    }
}