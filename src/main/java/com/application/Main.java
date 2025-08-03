package com.application;

import com.controller.MainController;
import com.controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/view/MainView.fxml"));
            Parent root = loader.load();

            MainController mainController = new MainController(primaryStage);
            MainViewController viewController = loader.getController();
            viewController.init(mainController, primaryStage);

            Scene scene = new Scene(root, 600, 400);

            // Tenta carregar o CSS, se existir
            try {
                scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            } catch (Exception e) {
                System.out.println("Aviso: styles.css não encontrado.");
            }

            primaryStage.setScene(scene);
            primaryStage.setTitle("Passagens Aéreas");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}