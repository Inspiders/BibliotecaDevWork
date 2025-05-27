package com.biblioteca;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;

public class BibliotecaGUI extends Application {
    private Label statusLabel;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Biblioteca");
        
        // Layout principal
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + Recursos.CINZA_CLARO + ";");
        
        // Adicionar imagem de fundo
        StackPane backgroundPane = new StackPane();
        ImageView background = Recursos.criarBackground(800, 600);
        background.setOpacity(0.1);
        backgroundPane.getChildren().add(background);
        
        // Cabeçalho
        VBox header = criarCabecalho();
        root.setTop(header);
        
        // Área central - Menu principal
        VBox areaCentral = criarAreaCentral();
        root.setCenter(areaCentral);
        
        // Rodapé
        HBox rodape = criarRodape();
        root.setBottom(rodape);
        
        // Configurar cena
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private VBox criarCabecalho() {
        VBox header = new VBox(10);
        header.setStyle(Recursos.ESTILO_CABECALHO);
        header.setAlignment(Pos.CENTER);
        
        // Logo
        ImageView logo = Recursos.criarLogo(80);
        
        Label titulo = new Label("📚 Sistema de Biblioteca");
        titulo.setStyle("-fx-text-fill: " + Recursos.BRANCO + "; -fx-font-size: 24px; -fx-font-weight: bold;");
        
        Label subtitulo = new Label("Gerencie sua biblioteca de forma prática e eficiente");
        subtitulo.setStyle("-fx-text-fill: " + Recursos.CINZA_CLARO + "; -fx-font-size: 14px;");
        
        header.getChildren().addAll(logo, titulo, subtitulo);
        return header;
    }
    
    private VBox criarAreaCentral() {
        VBox area = new VBox(20);
        area.setPadding(new Insets(30));
        area.setAlignment(Pos.CENTER);
        area.setStyle("-fx-background-color: " + Recursos.BRANCO + ";");
        
        // Container para os botões
        VBox botoesContainer = new VBox(15);
        botoesContainer.setAlignment(Pos.CENTER);
        botoesContainer.setMaxWidth(300);
        
        // Botões do menu principal
        Button btnEntrar = criarBotao("🔑 Entrar", Recursos.AZUL_CLARO);
        btnEntrar.setOnAction(e -> mostrarTelaLogin());
        
        Button btnCriarConta = criarBotao("➕ Criar Conta", Recursos.VERDE_SUAVE);
        btnCriarConta.setOnAction(e -> mostrarTelaCriarConta());
        
        Button btnAdmin = criarBotao("👨‍💼 Modo Administrador", Recursos.AZUL_ESCURO);
        btnAdmin.setOnAction(e -> mostrarTelaAdmin());
        
        // Adicionar efeito de sombra ao container
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(Recursos.AZUL_ESCURO, 0.3));
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        shadow.setRadius(5);
        botoesContainer.setEffect(shadow);
        
        botoesContainer.getChildren().addAll(btnEntrar, btnCriarConta, btnAdmin);
        area.getChildren().add(botoesContainer);
        
        return area;
    }
    
    private HBox criarRodape() {
        HBox rodape = new HBox();
        rodape.setPadding(new Insets(10, 20, 10, 20));
        rodape.setAlignment(Pos.CENTER_LEFT);
        rodape.setStyle(Recursos.ESTILO_RODAPE);
        
        statusLabel = new Label("✅ Sistema pronto para uso");
        statusLabel.setStyle("-fx-text-fill: " + Recursos.BRANCO + "; -fx-font-size: 12px;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label creditos = new Label("Biblioteca v1.0");
        creditos.setStyle("-fx-text-fill: " + Recursos.CINZA_CLARO + "; -fx-font-size: 12px;");
        
        rodape.getChildren().addAll(statusLabel, spacer, creditos);
        return rodape;
    }
    
    private Button criarBotao(String texto, String cor) {
        Button btn = new Button(texto);
        btn.setPrefWidth(250);
        btn.setPrefHeight(40);
        btn.setStyle(String.format(Recursos.ESTILO_BOTAO, cor));
        
        // Efeito hover
        btn.setOnMouseEntered(e -> {
            btn.setStyle(btn.getStyle() + "-fx-opacity: 0.8;");
        });
        
        btn.setOnMouseExited(e -> {
            btn.setStyle(btn.getStyle().replace("-fx-opacity: 0.8;", ""));
        });
        
        return btn;
    }
    
    private void mostrarTelaLogin() {
        new TelaLogin((Stage) statusLabel.getScene().getWindow());
    }
    
    private void mostrarTelaCriarConta() {
        // TODO: Implementar tela de criar conta
        statusLabel.setText("➕ Tela de criar conta em desenvolvimento");
    }
    
    private void mostrarTelaAdmin() {
        new TelaAdmin((Stage) statusLabel.getScene().getWindow());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} 