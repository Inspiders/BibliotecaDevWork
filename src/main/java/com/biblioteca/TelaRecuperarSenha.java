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

public class TelaRecuperarSenha {
    private Stage stage;
    private Label statusLabel;
    private List<Usuario> usuarios;

    public TelaRecuperarSenha(Stage primaryStage, List<Usuario> usuarios) {
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

        Label titulo = new Label("Recuperar Senha");
        titulo.setFont(Font.font("Arial", 22));
        titulo.setTextFill(Color.web("#E74C3C"));

        TextField campoEmail = new TextField();
        campoEmail.setPromptText("E-mail cadastrado");
        campoEmail.setPrefHeight(36);

        Button btnEnviar = new Button("Enviar recuperação");
        btnEnviar.setPrefWidth(200);
        btnEnviar.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white; -fx-font-size: 15px; -fx-background-radius: 8px; -fx-font-weight: bold;");
        btnEnviar.setOnAction(e -> recuperarSenha(campoEmail.getText()));

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
            btnEnviar,
            btnVoltar,
            statusLabel
        );

        Scene scene = new Scene(root, 400, 320);
        stage.setScene(scene);
        stage.setTitle("Recuperar Senha - Biblioteca");
        stage.show();
    }

    private void recuperarSenha(String email) {
        if (email.isEmpty()) {
            statusLabel.setText("Preencha o e-mail!");
            return;
        }
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                statusLabel.setText("E-mail de recuperação enviado!");
                return;
            }
        }
        statusLabel.setText("E-mail não encontrado!");
    }

    private void voltarLogin() {
        new TelaLogin(stage);
    }
} 