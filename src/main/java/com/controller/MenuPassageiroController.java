package com.controller;

import com.model.Passageiro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuPassageiroController {
    private MainController mainController;
    private Stage stage;
    private Passageiro passageiro;

    @FXML
    private Label lblBemVindo;

    public void init(MainController mainController, Stage stage, Passageiro passageiro) {
        this.mainController = mainController;
        this.stage = stage;
        this.passageiro = passageiro;
        lblBemVindo.setText("Bem-vindo, " + passageiro.getNome() + "!");
    }

    @FXML
    private void onVerVoos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/ListaVoosView.fxml"));
            Parent root = loader.load();

            Stage voosStage = new Stage();
            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            voosStage.setScene(scene);
            voosStage.setTitle("Voos Disponíveis");
            voosStage.initOwner(stage);

            ListaVoosController controller = loader.getController();
            controller.init(SistemaVendasPassagens.getInstance().listarVoosDisponiveis());

            voosStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onComprarPassagem() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/ComprarPassagemView.fxml"));
            Parent root = loader.load();

            Stage comprarStage = new Stage();
            ComprarPassagemController controller = loader.getController();
            controller.init(mainController, comprarStage, passageiro);

            Scene scene = new Scene(root, 400, 300);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            comprarStage.setScene(scene);
            comprarStage.setTitle("Comprar Passagem");
            comprarStage.initOwner(stage);
            comprarStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onVerPassagens() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/ListaPassagensView.fxml"));
            Parent root = loader.load();

            Stage passagensStage = new Stage();
            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            passagensStage.setScene(scene);
            passagensStage.setTitle("Minhas Passagens");
            passagensStage.initOwner(stage);

            ListaPassagensController controller = loader.getController();
            controller.init(SistemaVendasPassagens.getInstance().buscarPassagensPorPassageiro(passageiro.getId()));

            passagensStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelarPassagem() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/CancelarPassagemView.fxml"));
            Parent root = loader.load();

            Stage cancelarStage = new Stage();
            CancelarPassagemController controller = loader.getController();
            controller.init(SistemaVendasPassagens.getInstance().buscarPassagensPorPassageiro(passageiro.getId()), cancelarStage);

            Scene scene = new Scene(root, 500, 350);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            cancelarStage.setScene(scene);
            cancelarStage.setTitle("Cancelar Passagem");
            cancelarStage.initOwner(stage);
            cancelarStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAtualizarDados() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/AtualizarPassageiroView.fxml"));
            Parent root = loader.load();

            Stage atualizarStage = new Stage();
            AtualizarPassageiroController controller = loader.getController();
            controller.init(passageiro, atualizarStage);

            Scene scene = new Scene(root, 400, 320);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            atualizarStage.setScene(scene);
            atualizarStage.setTitle("Atualizar Dados");
            atualizarStage.initOwner(stage);
            atualizarStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSair() {
        stage.close();
    }
}