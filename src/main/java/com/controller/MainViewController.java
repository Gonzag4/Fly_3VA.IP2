package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainViewController {
    private MainController mainController;
    private Stage stage;

    public void init(MainController mainController, Stage stage) {
        this.mainController = mainController;
        this.stage = stage;
    }

    @FXML
    private Button btnLoginPassageiro;
    @FXML
    private Button btnCadastroPassageiro;
    @FXML
    private Button btnLoginEmpresa;
    @FXML
    private Button btnCadastroEmpresa;
    @FXML
    private Button btnSair;

    @FXML
    private void onLoginPassageiro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/LoginPassageiroView.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            LoginPassageiroController controller = loader.getController();
            controller.init(mainController, loginStage);

            Scene scene = new Scene(root, 400, 250);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            loginStage.setScene(scene);
            loginStage.setTitle("Login Passageiro");
            loginStage.initOwner(stage);
            loginStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCadastroPassageiro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/CadastroPassageiroView.fxml"));
            Parent root = loader.load();

            Stage cadastroStage = new Stage();
            CadastroPassageiroController controller = loader.getController();
            controller.init(mainController, cadastroStage);

            Scene scene = new Scene(root, 400, 350);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            cadastroStage.setScene(scene);
            cadastroStage.setTitle("Cadastro de Passageiro");
            cadastroStage.initOwner(stage);
            cadastroStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLoginEmpresa() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/LoginEmpresaView.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            LoginEmpresaController controller = loader.getController();
            controller.init(mainController, loginStage);

            Scene scene = new Scene(root, 400, 250);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            loginStage.setScene(scene);
            loginStage.setTitle("Login Empresa Aérea");
            loginStage.initOwner(stage);
            loginStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCadastroEmpresa() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/CadastroEmpresaView.fxml"));
            Parent root = loader.load();

            Stage cadastroStage = new Stage();
            CadastroEmpresaController controller = loader.getController();
            controller.init(mainController, cadastroStage);

            Scene scene = new Scene(root, 400, 300);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            cadastroStage.setScene(scene);
            cadastroStage.setTitle("Cadastro de Empresa Aérea");
            cadastroStage.initOwner(stage);
            cadastroStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSair() {
        stage.close();
    }
}