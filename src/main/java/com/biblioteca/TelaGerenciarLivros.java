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

public class TelaGerenciarLivros {
    private Stage stage;
    private Label statusLabel;
    private TableView<Livro> tabelaLivros;
    private ObservableList<Livro> livros;
    
    public TelaGerenciarLivros(Stage primaryStage) {
        this.stage = primaryStage;
        this.livros = FXCollections.observableArrayList();
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
        
        // Área central - Tabela de livros e formulário
        HBox areaCentral = criarAreaCentral();
        root.setCenter(areaCentral);
        
        // Rodapé
        HBox rodape = criarRodape();
        root.setBottom(rodape);
        
        // Configurar cena
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Gerenciar Livros - Sistema de Biblioteca");
        stage.show();
    }
    
    private VBox criarCabecalho() {
        VBox header = new VBox(10);
        header.setStyle(Recursos.ESTILO_CABECALHO);
        header.setAlignment(Pos.CENTER);
        
        // Logo e ícone de livros
        HBox logoContainer = new HBox(10);
        logoContainer.setAlignment(Pos.CENTER);
        
        ImageView logo = Recursos.criarLogo(60);
        ImageView bookIcon = Recursos.criarIconeLivro(40);
        
        logoContainer.getChildren().addAll(logo, bookIcon);
        
        Label titulo = new Label("📚 Gerenciar Livros");
        titulo.setStyle("-fx-text-fill: " + Recursos.BRANCO + "; -fx-font-size: 24px; -fx-font-weight: bold;");
        
        Label subtitulo = new Label("Adicione, edite ou remova livros do acervo");
        subtitulo.setStyle("-fx-text-fill: " + Recursos.CINZA_CLARO + "; -fx-font-size: 14px;");
        
        header.getChildren().addAll(logoContainer, titulo, subtitulo);
        return header;
    }
    
    private HBox criarAreaCentral() {
        HBox area = new HBox(20);
        area.setPadding(new Insets(20));
        area.setAlignment(Pos.CENTER);
        
        // Tabela de livros
        VBox tabelaContainer = new VBox(10);
        tabelaContainer.setStyle("-fx-background-color: " + Recursos.BRANCO + "; -fx-padding: 20;");
        tabelaContainer.setPrefWidth(500);
        
        Label tituloTabela = new Label("Livros Cadastrados");
        tituloTabela.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        tabelaLivros = new TableView<>();
        tabelaLivros.setItems(livros);
        tabelaLivros.setPrefHeight(400);
        
        // Colunas da tabela
        TableColumn<Livro, String> colunaTitulo = new TableColumn<>("Título");
        colunaTitulo.setCellValueFactory(cellData -> cellData.getValue().tituloProperty());
        
        TableColumn<Livro, String> colunaAutor = new TableColumn<>("Autor");
        colunaAutor.setCellValueFactory(cellData -> cellData.getValue().autorProperty());
        
        TableColumn<Livro, String> colunaStatus = new TableColumn<>("Status");
        colunaStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        
        tabelaLivros.getColumns().addAll(colunaTitulo, colunaAutor, colunaStatus);
        
        // Botões de ação da tabela
        HBox botoesTabela = new HBox(10);
        botoesTabela.setAlignment(Pos.CENTER);
        
        Button btnEditar = criarBotao("✏️ Editar", Recursos.AZUL_CLARO);
        btnEditar.setOnAction(e -> editarLivro());
        
        Button btnExcluir = criarBotao("🗑️ Excluir", Recursos.VERMELHO_SUAVE);
        btnExcluir.setOnAction(e -> excluirLivro());
        
        botoesTabela.getChildren().addAll(btnEditar, btnExcluir);
        
        tabelaContainer.getChildren().addAll(tituloTabela, tabelaLivros, botoesTabela);
        
        // Formulário de cadastro
        VBox formContainer = new VBox(15);
        formContainer.setStyle("-fx-background-color: " + Recursos.BRANCO + "; -fx-padding: 20;");
        formContainer.setPrefWidth(250);
        
        Label tituloForm = new Label("Novo Livro");
        tituloForm.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        TextField campoTitulo = new TextField();
        campoTitulo.setPromptText("Título");
        campoTitulo.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        TextField campoAutor = new TextField();
        campoAutor.setPromptText("Autor");
        campoAutor.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        TextField campoEditora = new TextField();
        campoEditora.setPromptText("Editora");
        campoEditora.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        TextField campoAno = new TextField();
        campoAno.setPromptText("Ano de Publicação");
        campoAno.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        ComboBox<String> comboStatus = new ComboBox<>();
        comboStatus.getItems().addAll("Disponível", "Emprestado", "Reservado");
        comboStatus.setPromptText("Status");
        comboStatus.setStyle(Recursos.ESTILO_CAMPO_TEXTO);
        
        Button btnAdicionar = criarBotao("➕ Adicionar", Recursos.VERDE_SUAVE);
        btnAdicionar.setOnAction(e -> adicionarLivro(
            campoTitulo.getText(),
            campoAutor.getText(),
            campoEditora.getText(),
            campoAno.getText(),
            comboStatus.getValue()
        ));
        
        Button btnVoltar = criarBotao("⬅️ Voltar", Recursos.AZUL_ESCURO);
        btnVoltar.setOnAction(e -> voltarTelaAdmin());
        
        formContainer.getChildren().addAll(
            tituloForm,
            campoTitulo,
            campoAutor,
            campoEditora,
            campoAno,
            comboStatus,
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
        
        statusLabel = new Label("✅ Gerenciamento de livros pronto para uso");
        statusLabel.setStyle("-fx-text-fill: " + Recursos.BRANCO + "; -fx-font-size: 12px;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label creditos = new Label("Biblioteca v1.0 - Gerenciamento de Livros");
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
    
    private void adicionarLivro(String titulo, String autor, String editora, String ano, String status) {
        if (titulo.isEmpty() || autor.isEmpty() || editora.isEmpty() || ano.isEmpty() || status == null) {
            statusLabel.setText("⚠️ Preencha todos os campos!");
            return;
        }
        
        // TODO: Implementar adição de livro
        statusLabel.setText("✅ Livro adicionado com sucesso!");
    }
    
    private void editarLivro() {
        Livro livro = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livro == null) {
            statusLabel.setText("⚠️ Selecione um livro para editar!");
            return;
        }
        
        // TODO: Implementar edição de livro
        statusLabel.setText("✏️ Editando livro...");
    }
    
    private void excluirLivro() {
        Livro livro = tabelaLivros.getSelectionModel().getSelectedItem();
        if (livro == null) {
            statusLabel.setText("⚠️ Selecione um livro para excluir!");
            return;
        }
        
        // TODO: Implementar exclusão de livro
        statusLabel.setText("🗑️ Livro excluído com sucesso!");
    }
    
    private void voltarTelaAdmin() {
        new TelaAdmin(stage);
    }
    
    // Classe interna para representar um livro
    public static class Livro {
        private final StringProperty titulo = new SimpleStringProperty();
        private final StringProperty autor = new SimpleStringProperty();
        private final StringProperty status = new SimpleStringProperty();
        
        public Livro(String titulo, String autor, String status) {
            this.titulo.set(titulo);
            this.autor.set(autor);
            this.status.set(status);
        }
        
        public StringProperty tituloProperty() { return titulo; }
        public StringProperty autorProperty() { return autor; }
        public StringProperty statusProperty() { return status; }
    }
} 