package com.controller;

import com.exceptions.PassageiroNaoEncontradoException;
import com.model.Passageiro;
import com.controller.MainController;
import com.controller.MainViewController;
import com.controller.SistemaVendasPassagens;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginPassageiroController {
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
            Passageiro passageiro = SistemaVendasPassagens.getInstance().loginPassageiro(login, senha);
            lblErro.setText("Bem-vindo, " + passageiro.getNome() + "!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/MenuPassageiroView.fxml"));
            Parent root = loader.load();
            Stage menuStage = new Stage();
            MenuPassageiroController controller = loader.getController();
            controller.init(mainController, menuStage, passageiro);

            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            menuStage.setScene(scene);
            menuStage.setTitle("Menu Passageiro");
            menuStage.initOwner(stage);
            menuStage.showAndWait();

            stage.close();
        } catch (PassageiroNaoEncontradoException e) {
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