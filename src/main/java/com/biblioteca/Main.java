package com.biblioteca;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        // Iniciar com a tela de login
        new TelaLogin(primaryStage);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} 