package com.biblioteca;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaLogin {
    private static final String VERMELHO = "#E74C3C";
    private static final String VERMELHO_CLARO = "#FDEDEC";
    private static final String BRANCO = "#FFFFFF";
    private static final String TEXTO_ESCURO = "#222";
    private static final String TEXTO_CLARO = "#B3A7E6";
    
    private Stage stage;
    private Label statusLabel;
    
    public TelaLogin(Stage primaryStage) {
        this.stage = primaryStage;
        criarTela();
    }
    
    private void criarTela() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #faf9f8;");

        HBox mainContent = new HBox();
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setSpacing(0);
        mainContent.setFillHeight(true);

        VBox leftPane = new VBox(20);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setFillWidth(true);
        leftPane.setStyle("-fx-background-color: " + VERMELHO + ";");
        leftPane.setPadding(new Insets(40, 10, 40, 10));
        leftPane.setMinWidth(260);
        leftPane.setPrefWidth(320);
        leftPane.setMaxWidth(400);

        ImageView logo = new ImageView(new Image("https://cdn-icons-png.flaticon.com/512/29/29302.png", 90, 90, true, true));
        Label nomeSistema = new Label("Biblioteca");
        nomeSistema.setTextFill(Color.WHITE);
        nomeSistema.setFont(Font.font("Arial", 30));
        Label subtitulo = new Label("Gerenciamento de Livros");
        subtitulo.setTextFill(Color.web("#ffeaea"));
        subtitulo.setFont(Font.font("Arial", 16));
        leftPane.getChildren().addAll(logo, nomeSistema, subtitulo);

        VBox rightPane = new VBox();
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setFillWidth(true);
        rightPane.setStyle("-fx-background-color: #fff; -fx-background-radius: 18px;");
        rightPane.setPadding(new Insets(40, 40, 40, 40));
        rightPane.setSpacing(0);
        rightPane.setMinWidth(280);
        rightPane.setPrefWidth(400);
        rightPane.setMaxWidth(500);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(VERMELHO, 0.10));
        shadow.setRadius(18);
        rightPane.setEffect(shadow);

        VBox form = new VBox(12);
        form.setAlignment(Pos.CENTER_LEFT);
        form.setFillWidth(true);

        Label titulo = new Label("Entrar no Sistema");
        titulo.setFont(Font.font("Arial", 22));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        TextField campoUsuario = new TextField();
        campoUsuario.setPromptText("Usuário");
        campoUsuario.setPrefHeight(38);
        campoUsuario.setStyle("-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #eee; -fx-font-size: 15px;");

        PasswordField campoPin = new PasswordField();
        campoPin.setPromptText("PIN");
        campoPin.setPrefHeight(38);
        campoPin.setStyle("-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-color: #eee; -fx-font-size: 15px;");

        HBox forgotBox = new HBox();
        forgotBox.setAlignment(Pos.CENTER_LEFT);
        Hyperlink forgotLink = new Hyperlink("Esqueceu a senha?");
        forgotLink.setBorder(Border.EMPTY);
        forgotLink.setTextFill(Color.web(VERMELHO));
        forgotLink.setOnAction(e -> statusLabel.setText("Função de recuperação não implementada."));
        forgotBox.getChildren().add(forgotLink);

        Button btnLogin = new Button("Entrar");
        btnLogin.setPrefWidth(Double.MAX_VALUE);
        btnLogin.setPrefHeight(38);
        btnLogin.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 8px; -fx-font-weight: bold;");
        btnLogin.setOnAction(e -> fazerLogin(campoUsuario.getText(), campoPin.getText()));

        Button btnSignUp = new Button("Não tem uma conta? Cadastre-se");
        btnSignUp.setPrefWidth(Double.MAX_VALUE);
        btnSignUp.setPrefHeight(38);
        btnSignUp.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-text-fill: " + VERMELHO + "; -fx-font-size: 15px; -fx-background-radius: 8px; -fx-border-color: " + VERMELHO + "; -fx-border-width: 1px; -fx-font-weight: bold;");
        btnSignUp.setOnAction(e -> new TelaCadastro(stage));

        Hyperlink adminLink = new Hyperlink("Entrar como administrador");
        adminLink.setTextFill(Color.web(VERMELHO));
        adminLink.setBorder(Border.EMPTY);
        adminLink.setOnAction(e -> statusLabel.setText("Função de admin não implementada."));
        adminLink.setStyle("-fx-font-size: 13px; -fx-underline: true; -fx-padding: 0 0 0 2;");

        statusLabel = new Label("");
        statusLabel.setTextFill(Color.web(VERMELHO));
        statusLabel.setFont(Font.font("Arial", 13));

        form.getChildren().addAll(
            titulo,
            campoUsuario,
            campoPin,
            forgotBox,
            btnLogin,
            btnSignUp,
            adminLink,
            statusLabel
        );
        rightPane.getChildren().add(form);

        mainContent.getChildren().addAll(leftPane, rightPane);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 900, 600);
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            double w = newVal.doubleValue();
            if (w < 600) {
                leftPane.setVisible(false);
                rightPane.setPadding(new Insets(20, 10, 20, 10));
                rightPane.setMaxWidth(500);
                form.setPadding(new Insets(10, 5, 10, 5));
                btnLogin.setFont(Font.font("Arial", 13));
                btnSignUp.setFont(Font.font("Arial", 12));
            } else {
                leftPane.setVisible(true);
                rightPane.setPadding(new Insets(40, 40, 40, 40));
                rightPane.setMaxWidth(500);
                form.setPadding(new Insets(0, 0, 0, 0));
                btnLogin.setFont(Font.font("Arial", 16));
                btnSignUp.setFont(Font.font("Arial", 15));
            }
        });
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            double h = newVal.doubleValue();
            if (h < 500) {
                mainContent.setAlignment(Pos.TOP_CENTER);
            } else {
                mainContent.setAlignment(Pos.CENTER);
            }
        });

        stage.setScene(scene);
        stage.setTitle("Login - Biblioteca");
        stage.show();
    }
    
    private void fazerLogin(String usuario, String pin) {
        if (usuario.isEmpty() || pin.isEmpty()) {
            statusLabel.setText("Preencha todos os campos!");
            return;
        }
        int idx = User.autenticar(usuario, pin);
        if (idx >= 0) {
            statusLabel.setText("Login realizado! Bem-vindo, " + usuario + ".");
            // Verificar se é admin ou usuário comum
            if (usuario.equals("admin")) {
                new TelaPrincipalAdmin(stage, usuario);
            } else {
                new TelaPrincipalUsuario(stage, usuario);
            }
        } else {
            statusLabel.setText("Usuário ou PIN inválidos!");
        }
    }
} 