package com.controller;

import com.model.EmpresaAerea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuEmpresaController {
    private Stage stage;
    private EmpresaAerea empresa;


    public void init(Stage stage, EmpresaAerea empresa) {
        this.stage = stage;
        this.empresa = empresa;
    }

    @FXML
    private void onCadastrarAeronave() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/CadastrarAeronaveView.fxml"));
            javafx.scene.Parent root = loader.load();

            Stage cadastroStage = new Stage();
            CadastrarAeronaveController controller = loader.getController();
            controller.init(cadastroStage, empresa);

            javafx.scene.Scene scene = new javafx.scene.Scene(root, 400, 300);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            cadastroStage.setScene(scene);
            cadastroStage.setTitle("Cadastrar Aeronave");
            cadastroStage.initOwner(stage);
            cadastroStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCadastrarVooNacional() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/CadastrarVooNacionalView.fxml"));
            javafx.scene.Parent root = loader.load();

            Stage cadastroStage = new Stage();
            CadastrarVooNacionalController controller = loader.getController();
            controller.init(cadastroStage, empresa.getId());

            javafx.scene.Scene scene = new javafx.scene.Scene(root, 500, 400);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            cadastroStage.setScene(scene);
            cadastroStage.setTitle("Cadastrar Voo Nacional");
            cadastroStage.initOwner(stage);
            cadastroStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCadastrarVooInternacional() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/CadastrarVooInternacionalView.fxml"));
            javafx.scene.Parent root = loader.load();

            Stage cadastroStage = new Stage();
            CadastrarVooInternacionalController controller = loader.getController();
            controller.init(cadastroStage, empresa.getId());

            javafx.scene.Scene scene = new javafx.scene.Scene(root, 500, 450);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            cadastroStage.setScene(scene);
            cadastroStage.setTitle("Cadastrar Voo Internacional");
            cadastroStage.initOwner(stage);
            cadastroStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onListarVoos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListaVoosView.fxml"));
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
    private void onListarAeronaves() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListaAeronavesView.fxml"));
            Parent root = loader.load();

            Stage aeronavesStage = new Stage();
            Scene scene = new Scene(root, 500, 400);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            aeronavesStage.setScene(scene);
            aeronavesStage.setTitle("Aeronaves da Empresa");
            aeronavesStage.initOwner(stage);

            ListaAeronavesController controller = loader.getController();
            // Filtra aeronaves da empresa logada
            var todasAeronaves = SistemaVendasPassagens.getInstance().listarAeronaves();
            var aeronavesEmpresa = todasAeronaves.stream()
                    .filter(a -> a.getIdEmpresa() == empresa.getId())
                    .toList();
            controller.init(aeronavesEmpresa, empresa.getId(), aeronavesStage);

            aeronavesStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAtualizarDados() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AtualizarEmpresaView.fxml"));
            Parent root = loader.load();

            Stage atualizarStage = new Stage();
            AtualizarEmpresaController controller = loader.getController();
            controller.init(atualizarStage, empresa);

            Scene scene = new Scene(root, 400, 350);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            atualizarStage.setScene(scene);
            atualizarStage.setTitle("Atualizar Dados da Empresa");
            atualizarStage.initOwner(stage);
            atualizarStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSair() {
        if (stage != null) stage.close();
    }
}