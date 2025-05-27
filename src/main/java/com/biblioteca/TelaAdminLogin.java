package com.biblioteca;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class TelaAdminLogin {
    private Stage stage;
    private Label statusLabel;
    private List<Usuario> usuarios;

    public TelaAdminLogin(Stage primaryStage, List<Usuario> usuarios) {
        this.stage = primaryStage;
        this.usuarios = usuarios;
        criarTela();
    }

    private void criarTela() {
        VBox root = new VBox(18);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40, 40, 40, 40));
        root.setStyle("-fx-background-color: #fff; -fx-background-radius: 18px;");
        root.setPrefWidth(400);

        Label titulo = new Label("Login de Administrador");
        titulo.setFont(Font.font("Arial", 22));
        titulo.setTextFill(Color.web("#E74C3C"));

        TextField campoEmail = new TextField();
        campoEmail.setPromptText("E-mail do administrador");
        campoEmail.setPrefHeight(36);

        PasswordField campoSenha = new PasswordField();
        campoSenha.setPromptText("Senha");
        campoSenha.setPrefHeight(36);

        Button btnEntrar = new Button("Entrar como Admin");
        btnEntrar.setPrefWidth(200);
        btnEntrar.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-font-size: 15px; -fx-background-radius: 8px; -fx-font-weight: bold;");
        btnEntrar.setOnAction(e -> fazerLoginAdmin(campoEmail.getText(), campoSenha.getText()));

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setPrefWidth(200);
        btnVoltar.setStyle("-fx-background-color: #FDEDEC; -fx-text-fill: #E74C3C; -fx-font-size: 14px; -fx-background-radius: 8px; -fx-border-color: #E74C3C; -fx-border-width: 1px; -fx-font-weight: bold;");
        btnVoltar.setOnAction(e -> voltarLogin());

        statusLabel = new Label("");
        statusLabel.setTextFill(Color.web("#E74C3C"));
        statusLabel.setFont(Font.font("Arial", 13));

        root.getChildren().addAll(
            titulo,
            campoEmail,
            campoSenha,
            btnEntrar,
            btnVoltar,
            statusLabel
        );

        Scene scene = new Scene(root, 400, 340);
        stage.setScene(scene);
        stage.setTitle("Admin - Biblioteca");
        stage.show();
    }

    private void fazerLoginAdmin(String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.isAdmin() && u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)) {
                statusLabel.setText("Login de administrador realizado!");
                // Aqui você pode abrir a tela de administração
                return;
            }
        }
        statusLabel.setText("Credenciais de administrador inválidas!");
    }

    private void voltarLogin() {
        new TelaLogin(stage);
    }
} 