package com.application;

import com.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        MainController controller = new MainController(primaryStage);
        controller.mostrarTelaPrincipal();
    }

    public static void main(String[] args) {
        launch(args);
    }
}