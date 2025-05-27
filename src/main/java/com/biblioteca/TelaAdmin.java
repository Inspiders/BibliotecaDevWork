package com.biblioteca;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;

public class TelaAdmin {
    private Stage stage;
    private Label statusLabel;
    
    public TelaAdmin(Stage primaryStage) {
        this.stage = primaryStage;
        criarTela();
    }
    
    private void criarTela() {
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
        
        // Área central - Menu do administrador
        VBox areaCentral = criarAreaCentral();
        root.setCenter(areaCentral);
        
        // Rodapé
        HBox rodape = criarRodape();
        root.setBottom(rodape);
        
        // Configurar cena
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Administrador - Sistema de Biblioteca");
        stage.show();
    }
    
    private VBox criarCabecalho() {
        VBox header = new VBox(10);
        header.setStyle(Recursos.ESTILO_CABECALHO);
        header.setAlignment(Pos.CENTER);
        
        // Logo e ícone de admin
        HBox logoContainer = new HBox(10);
        logoContainer.setAlignment(Pos.CENTER);
        
        ImageView logo = Recursos.criarLogo(60);
        ImageView adminIcon = Recursos.criarIconeAdmin(40);
        
        logoContainer.getChildren().addAll(logo, adminIcon);
        
        Label titulo = new Label("👨‍💼 Painel do Administrador");
        titulo.setStyle("-fx-text-fill: " + Recursos.BRANCO + "; -fx-font-size: 24px; -fx-font-weight: bold;");
        
        Label subtitulo = new Label("Gerencie usuários, livros e empréstimos");
        subtitulo.setStyle("-fx-text-fill: " + Recursos.CINZA_CLARO + "; -fx-font-size: 14px;");
        
        header.getChildren().addAll(logoContainer, titulo, subtitulo);
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
        
        // Botões do menu do administrador
        Button btnGerenciarUsuarios = criarBotao("👥 Gerenciar Usuários", Recursos.AZUL_CLARO);
        btnGerenciarUsuarios.setOnAction(e -> gerenciarUsuarios());
        
        Button btnGerenciarLivros = criarBotao("📚 Gerenciar Livros", Recursos.VERDE_SUAVE);
        btnGerenciarLivros.setOnAction(e -> gerenciarLivros());
        
        Button btnGerenciarEmprestimos = criarBotao("📖 Gerenciar Empréstimos", Recursos.AZUL_ESCURO);
        btnGerenciarEmprestimos.setOnAction(e -> gerenciarEmprestimos());
        
        Button btnRelatorios = criarBotao("📊 Relatórios", Recursos.VERMELHO_SUAVE);
        btnRelatorios.setOnAction(e -> mostrarRelatorios());
        
        Button btnVoltar = criarBotao("⬅️ Voltar", Recursos.CINZA_CLARO);
        btnVoltar.setStyle(String.format(Recursos.ESTILO_BOTAO, Recursos.AZUL_ESCURO));
        btnVoltar.setOnAction(e -> voltarMenuPrincipal());
        
        // Adicionar efeito de sombra ao container
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(Recursos.AZUL_ESCURO, 0.3));
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
        shadow.setRadius(5);
        botoesContainer.setEffect(shadow);
        
        botoesContainer.getChildren().addAll(
            btnGerenciarUsuarios,
            btnGerenciarLivros,
            btnGerenciarEmprestimos,
            btnRelatorios,
            btnVoltar
        );
        
        area.getChildren().add(botoesContainer);
        return area;
    }
    
    private HBox criarRodape() {
        HBox rodape = new HBox();
        rodape.setPadding(new Insets(10, 20, 10, 20));
        rodape.setAlignment(Pos.CENTER_LEFT);
        rodape.setStyle(Recursos.ESTILO_RODAPE);
        
        statusLabel = new Label("✅ Painel do administrador pronto para uso");
        statusLabel.setStyle("-fx-text-fill: " + Recursos.BRANCO + "; -fx-font-size: 12px;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label creditos = new Label("Biblioteca v1.0 - Modo Administrador");
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
    
    private void gerenciarUsuarios() {
        new TelaGerenciarUsuarios(stage);
    }
    
    private void gerenciarLivros() {
        new TelaGerenciarLivros(stage);
    }
    
    private void gerenciarEmprestimos() {
        // TODO: Implementar gerenciamento de empréstimos
        statusLabel.setText("📖 Gerenciamento de empréstimos em desenvolvimento");
    }
    
    private void mostrarRelatorios() {
        // TODO: Implementar relatórios
        statusLabel.setText("📊 Relatórios em desenvolvimento");
    }
    
    private void voltarMenuPrincipal() {
        // TODO: Implementar retorno ao menu principal
        statusLabel.setText("Voltando ao menu principal...");
    }
} 