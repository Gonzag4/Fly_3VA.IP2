package com.controller;

import com.exceptions.EmpresaAereaNaoEncontradaException;
import com.model.EmpresaAerea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginEmpresaController {
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblErro;

    private MainController mainController;
    private Stage stage;

    public void init(MainController mainController, Stage stage) {
        this.mainController = mainController;
        this.stage = stage;
    }

    @FXML
    private void onEntrar() {
        String login = txtLogin.getText();
        String senha = txtSenha.getText();

        try {
            EmpresaAerea empresa = SistemaVendasPassagens.getInstance().loginEmpresa(login, senha);
            lblErro.setText("Bem-vindo, " + empresa.getNome() + "!");

            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/MenuEmpresaView.fxml"));
            javafx.scene.Parent root = loader.load();
            Stage menuStage = new Stage();
            com.controller.MenuEmpresaController controller = loader.getController();
            controller.init(menuStage, empresa);

            javafx.scene.Scene scene = new javafx.scene.Scene(root, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            menuStage.setScene(scene);
            menuStage.setTitle("Menu Empresa");
            menuStage.initOwner(stage);
            menuStage.showAndWait();

            stage.close();
        } catch (EmpresaAereaNaoEncontradaException e) {
            lblErro.setText("Erro no login: " + e.getMessage());
        } catch (Exception e) {
            lblErro.setText("Erro ao abrir menu: " + e.getMessage());
        }
    }

    @FXML
    private void onCancelar() {
        stage.close();
    }
}