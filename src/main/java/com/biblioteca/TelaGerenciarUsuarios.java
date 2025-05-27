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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TelaGerenciarUsuarios {
    private Stage stage;
    private Label statusLabel;
    private TableView<Usuario> tabelaUsuarios;
    private ObservableList<Usuario> usuarios;
    
    public TelaGerenciarUsuarios(Stage primaryStage) {
        this.stage = primaryStage;
        this.usuarios = FXCollections.observableArrayList();
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
        
        // Área central - Tabela de usuários e formulário
        HBox areaCentral = criarAreaCentral();
        root.setCenter(areaCentral);
        
        // Rodapé
        HBox rodape = criarRodape();
        root.setBottom(rodape);
        
        // Configurar cena
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Gerenciar Usuários - Sistema de Biblioteca");
        stage.show();
    }
    
    private VBox criarCabecalho() {
        VBox header = new VBox(10);
        header.setStyle(Recursos.ESTILO_CABECALHO);
        header.setAlignment(Pos.CENTER);
        
        // Logo e ícone de usuários
        HBox logoContainer = new HBox(10);
        logoContainer.setAlignment(Pos.CENTER);
        
        ImageView logo = Recursos.criarLogo(60);
        ImageView userIcon = Recursos.criarIconeUsuario(40);
        
        logoContainer.getChildren().addAll(logo, userIcon);
        
        Label titulo = new Label("👥 Gerenciar Usuários");
        titulo.setStyle("-fx-text-fill: " + Recursos.BRANCO + "; -fx-font-size: 24px; -fx-font-weight: bold;");
        
        Label subtitulo = new Label("Adicione, edite ou remova usuários do sistema");
        subtitulo.setStyle("-fx-text-fill: " + Recursos.CINZA_CLARO + "; -fx-font-size: 14px;");
        
        header.getChildren().addAll(logoContainer, titulo, subtitulo);
        return header;
    }
    
    private HBox criarAreaCentral() {
        HBox area = new HBox(20);
        area.setPadding(new Insets(20));
        area.setAlignment(Pos.CENTER);
        
        // Tabela de usuários
        VBox tabelaContainer = new VBox(10);
        tabelaContainer.setStyle("-fx-background-color: " + Recursos.BRANCO + "; -fx-padding: 20;");
        tabelaContainer.setPrefWidth(500);
        
        Label tituloTabela = new Label("Usuários Cadastrados");
        tituloTabela.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        tabelaUsuarios = new TableView<>();
        tabelaUsuarios.setItems(usuarios);
        tabelaUsuarios.setPrefHeight(400);
        
        // Colunas da tabela
        TableColumn<Usuario, String> colunaNome = new TableColumn<>("Nome");
        colunaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        
        TableColumn<Usuario, String> colunaEmail = new TableColumn<>("Email");
        colunaEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        
        TableColumn<Usuario, String> colunaTipo = new TableColumn<>("Tipo");
        colunaTipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        
        tabelaUsuarios.getColumns().addAll(colunaNome, colunaEmail, colunaTipo);
        
        // Botões de ação da tabela
        HBox botoesTabela = new HBox(10);
        botoesTabela.setAlignment(Pos.CENTER);
        
        Button btnEditar = criarBotao("✏️ Editar", Recursos.AZUL_CLARO);
        btnEditar.setOnAction(e -> editarUsuario());
        
        Button btnExcluir = criarBotao("🗑️ Excluir", Recursos.VERMELHO_SUAVE);
        btnExcluir.setOnAction(e -> excluirUsuario());
        
        botoesTabela.getChildren().addAll(btnEditar, btnExcluir);
        
        tabelaContainer.getChildren().addAll(tituloTabela, tabelaUsuarios, botoesTabela);
        
        // Formulário de cadastro
        VBox formContainer = new VBox(15);
        formContainer.setStyle("-fx-background-color: " + Recursos.BRANCO + "; -fx-padding: 20;");
        formContainer.setPrefWidth(250);
        
        Label tituloForm = new Label("Novo Usuário");
        tituloForm.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        TextField campoNome = new TextField();
        campoNome.setPromptText("Nome");
        campoNome.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        TextField campoEmail = new TextField();
        campoEmail.setPromptText("Email");
        campoEmail.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        PasswordField campoSenha = new PasswordField();
        campoSenha.setPromptText("Senha");
        campoSenha.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        ComboBox<String> comboTipo = new ComboBox<>();
        comboTipo.getItems().addAll("Usuário", "Administrador");
        comboTipo.setPromptText("Tipo de Usuário");
        comboTipo.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        Button btnAdicionar = criarBotao("➕ Adicionar", Recursos.VERDE_SUAVE);
        btnAdicionar.setOnAction(e -> adicionarUsuario(
            campoNome.getText(),
            campoEmail.getText(),
            campoSenha.getText(),
            comboTipo.getValue()
        ));
        
        Button btnVoltar = criarBotao("⬅️ Voltar", Recursos.AZUL_ESCURO);
        btnVoltar.setOnAction(e -> voltarTelaAdmin());
        
        formContainer.getChildren().addAll(
            tituloForm,
            campoNome,
            campoEmail,
            campoSenha,
            comboTipo,
            btnAdicionar,
            btnVoltar
        );
        
        area.getChildren().addAll(tabelaContainer, formContainer);
        return area;
    }
    
    private HBox criarRodape() {
        HBox rodape = new HBox();
        rodape.setPadding(new Insets(10, 20, 10, 20));
        rodape.setAlignment(Pos.CENTER_LEFT);
        rodape.setStyle(Recursos.ESTILO_RODAPE);
        
        statusLabel = new Label("✅ Gerenciamento de usuários pronto para uso");
        statusLabel.setStyle("-fx-text-fill: " + Recursos.BRANCO + "; -fx-font-size: 12px;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label creditos = new Label("Biblioteca v1.0 - Gerenciamento de Usuários");
        creditos.setStyle("-fx-text-fill: " + Recursos.CINZA_CLARO + "; -fx-font-size: 12px;");
        
        rodape.getChildren().addAll(statusLabel, spacer, creditos);
        return rodape;
    }
    
    private Button criarBotao(String texto, String cor) {
        Button btn = new Button(texto);
        btn.setPrefWidth(200);
        btn.setPrefHeight(35);
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
    
    private void adicionarUsuario(String nome, String email, String senha, String tipo) {
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || tipo == null) {
            statusLabel.setText("⚠️ Preencha todos os campos!");
            return;
        }
        
        // TODO: Implementar adição de usuário
        statusLabel.setText("✅ Usuário adicionado com sucesso!");
    }
    
    private void editarUsuario() {
        Usuario usuario = tabelaUsuarios.getSelectionModel().getSelectedItem();
        if (usuario == null) {
            statusLabel.setText("⚠️ Selecione um usuário para editar!");
            return;
        }
        
        // TODO: Implementar edição de usuário
        statusLabel.setText("✏️ Editando usuário...");
    }
    
    private void excluirUsuario() {
        Usuario usuario = tabelaUsuarios.getSelectionModel().getSelectedItem();
        if (usuario == null) {
            statusLabel.setText("⚠️ Selecione um usuário para excluir!");
            return;
        }
        
        // TODO: Implementar exclusão de usuário
        statusLabel.setText("🗑️ Usuário excluído com sucesso!");
    }
    
    private void voltarTelaAdmin() {
        new TelaAdmin(stage);
    }
    
    // Classe interna para representar um usuário
    public static class Usuario {
        private final StringProperty nome = new SimpleStringProperty();
        private final StringProperty email = new SimpleStringProperty();
        private final StringProperty tipo = new SimpleStringProperty();
        
        public Usuario(String nome, String email, String tipo) {
            this.nome.set(nome);
            this.email.set(email);
            this.tipo.set(tipo);
        }
        
        public StringProperty nomeProperty() { return nome; }
        public StringProperty emailProperty() { return email; }
        public StringProperty tipoProperty() { return tipo; }
    }
} 