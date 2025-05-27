package com.biblioteca;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaCadastro {
    private static final String VERMELHO = "#E74C3C";
    private static final String VERMELHO_CLARO = "#FDEDEC";
    private static final String BRANCO = "#FFFFFF";
    private static final String TEXTO_ESCURO = "#222";
    private static final String TEXTO_CLARO = "#B3A7E6";
    
    private Stage stage;
    private Label statusLabel;
    
    public TelaCadastro(Stage primaryStage) {
        this.stage = primaryStage;
        criarTela();
    }
    
    private void criarTela() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #faf9f8;");

        VBox mainContent = new VBox(20);
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(40, 40, 40, 40));
        mainContent.setStyle("-fx-background-color: #fff; -fx-background-radius: 18px;");
        mainContent.setMinWidth(280);
        mainContent.setPrefWidth(400);
        mainContent.setMaxWidth(500);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(VERMELHO, 0.10));
        shadow.setRadius(18);
        mainContent.setEffect(shadow);

        Label titulo = new Label("Cadastro de Usuário");
        titulo.setFont(Font.font("Arial", 22));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        TextField campoNome = new TextField();
        campoNome.setPromptText("Nome");
        campoNome.setPrefHeight(38);
        campoNome.setStyle("-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #eee; -fx-font-size: 15px;");

        TextField campoEmail = new TextField();
        campoEmail.setPromptText("Email");
        campoEmail.setPrefHeight(38);
        campoEmail.setStyle("-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #eee; -fx-font-size: 15px;");

        PasswordField campoPin = new PasswordField();
        campoPin.setPromptText("PIN");
        campoPin.setPrefHeight(38);
        campoPin.setStyle("-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #eee; -fx-font-size: 15px;");

        PasswordField campoConfirmarPin = new PasswordField();
        campoConfirmarPin.setPromptText("Confirmar PIN");
        campoConfirmarPin.setPrefHeight(38);
        campoConfirmarPin.setStyle("-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #eee; -fx-font-size: 15px;");

        Button btnCadastrar = new Button("Cadastrar");
        btnCadastrar.setPrefWidth(Double.MAX_VALUE);
        btnCadastrar.setPrefHeight(38);
        btnCadastrar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 8px; -fx-font-weight: bold;");
        btnCadastrar.setOnAction(e -> fazerCadastro(campoNome.getText(), campoPin.getText(), campoConfirmarPin.getText()));

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setPrefWidth(Double.MAX_VALUE);
        btnVoltar.setPrefHeight(38);
        btnVoltar.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-text-fill: " + VERMELHO + "; -fx-font-size: 15px; -fx-background-radius: 8px; -fx-border-color: " + VERMELHO + "; -fx-border-width: 1px; -fx-font-weight: bold;");
        btnVoltar.setOnAction(e -> voltarParaLogin());

        statusLabel = new Label("");
        statusLabel.setTextFill(Color.web(VERMELHO));
        statusLabel.setFont(Font.font("Arial", 13));

        mainContent.getChildren().addAll(
            titulo,
            campoNome,
            campoEmail,
            campoPin,
            campoConfirmarPin,
            btnCadastrar,
            btnVoltar,
            statusLabel
        );
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Cadastro - Biblioteca");
        stage.show();
    }
    
    private void fazerCadastro(String usuario, String pin, String confirmarPin) {
        if (usuario.isEmpty() || pin.isEmpty() || confirmarPin.isEmpty()) {
            statusLabel.setText("Preencha todos os campos!");
            return;
        }
        if (!pin.equals(confirmarPin)) {
            statusLabel.setText("Os PINs não coincidem!");
            return;
        }
        User.adicionarUsuario(usuario, pin);
        statusLabel.setText("Cadastro realizado! Você pode fazer login agora.");
    }
    
    private void voltarParaLogin() {
        new TelaLogin(stage);
    }
} 