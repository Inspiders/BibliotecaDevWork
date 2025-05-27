package com.biblioteca;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaPrincipalAdmin {
    private static final String VERMELHO = "#E74C3C";
    private static final String VERMELHO_CLARO = "#FDEDEC";
    private static final String BRANCO = "#FFFFFF";
    private static final String TEXTO_ESCURO = "#222";
    private static final String TEXTO_CLARO = "#B3A7E6";
    
    private Stage stage;
    private String usuario;
    private VBox contentArea;
    
    public TelaPrincipalAdmin(Stage primaryStage, String usuario) {
        this.stage = primaryStage;
        this.usuario = usuario;
        criarTela();
    }
    
    private void criarTela() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #faf9f8;");

        // Menu lateral
        VBox sidebar = criarSidebar();
        sidebar.setPrefWidth(250);
        sidebar.setStyle("-fx-background-color: white; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        root.setLeft(sidebar);

        // Área de conteúdo principal
        contentArea = new VBox(20);
        contentArea.setPadding(new Insets(30));
        contentArea.setStyle("-fx-background-color: #faf9f8;");
        root.setCenter(contentArea);

        // Mostrar tela inicial
        mostrarTelaInicial();

        Scene scene = new Scene(root, 1200, 800);
        stage.setScene(scene);
        stage.setTitle("Biblioteca - Administrador: " + usuario);
        stage.show();
    }

    private VBox criarSidebar() {
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(20));
        sidebar.setAlignment(Pos.TOP_CENTER);

        // Logo e título
        ImageView logo = new ImageView(new Image("https://cdn-icons-png.flaticon.com/512/29/29302.png", 60, 60, true, true));
        Label titulo = new Label("Biblioteca");
        titulo.setFont(Font.font("Arial", 20));
        titulo.setTextFill(Color.web(VERMELHO));

        // Botões do menu
        Button btnInicio = criarBotaoMenu("Início", "🏠");
        Button btnLivros = criarBotaoMenu("Gerenciar Livros", "📚");
        Button btnUsuarios = criarBotaoMenu("Gerenciar Usuários", "👥");
        Button btnEmprestimos = criarBotaoMenu("Gerenciar Empréstimos", "📖");
        Button btnRelatorios = criarBotaoMenu("Relatórios", "📊");
        Button btnPerfil = criarBotaoMenu("Meu Perfil", "👤");
        Button btnSair = criarBotaoMenu("Sair", "🚪");

        // Ações dos botões
        btnInicio.setOnAction(e -> mostrarTelaInicial());
        btnLivros.setOnAction(e -> mostrarGerenciarLivros());
        btnUsuarios.setOnAction(e -> mostrarGerenciarUsuarios());
        btnEmprestimos.setOnAction(e -> mostrarGerenciarEmprestimos());
        btnRelatorios.setOnAction(e -> mostrarRelatorios());
        btnPerfil.setOnAction(e -> mostrarPerfil());
        btnSair.setOnAction(e -> voltarParaLogin());

        sidebar.getChildren().addAll(
            logo, titulo,
            new Separator(),
            btnInicio, btnLivros, btnUsuarios,
            btnEmprestimos, btnRelatorios, btnPerfil,
            new Separator(),
            btnSair
        );

        return sidebar;
    }

    private Button criarBotaoMenu(String texto, String icone) {
        Button btn = new Button(icone + "  " + texto);
        btn.setPrefWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + TEXTO_ESCURO + "; -fx-font-size: 14px; -fx-padding: 10;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-text-fill: " + VERMELHO + "; -fx-font-size: 14px; -fx-padding: 10;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + TEXTO_ESCURO + "; -fx-font-size: 14px; -fx-padding: 10;"));
        return btn;
    }

    private void mostrarTelaInicial() {
        contentArea.getChildren().clear();
        
        Label bemVindo = new Label("Bem-vindo, Administrador " + usuario + "!");
        bemVindo.setFont(Font.font("Arial", 24));
        bemVindo.setTextFill(Color.web(TEXTO_ESCURO));

        Label subtitulo = new Label("Painel de Controle");
        subtitulo.setFont(Font.font("Arial", 16));
        subtitulo.setTextFill(Color.web(TEXTO_ESCURO));

        HBox cards = new HBox(20);
        cards.setAlignment(Pos.CENTER);

        // Card de Livros
        VBox cardLivros = criarCard("📚", "Gerenciar Livros", "Adicione, remova ou edite livros");
        cardLivros.setOnMouseClicked(e -> mostrarGerenciarLivros());

        // Card de Usuários
        VBox cardUsuarios = criarCard("👥", "Gerenciar Usuários", "Gerencie contas de usuários");
        cardUsuarios.setOnMouseClicked(e -> mostrarGerenciarUsuarios());

        // Card de Empréstimos
        VBox cardEmprestimos = criarCard("📖", "Gerenciar Empréstimos", "Controle de empréstimos e devoluções");
        cardEmprestimos.setOnMouseClicked(e -> mostrarGerenciarEmprestimos());

        // Card de Relatórios
        VBox cardRelatorios = criarCard("📊", "Relatórios", "Visualize relatórios do sistema");
        cardRelatorios.setOnMouseClicked(e -> mostrarRelatorios());

        cards.getChildren().addAll(cardLivros, cardUsuarios, cardEmprestimos, cardRelatorios);

        contentArea.getChildren().addAll(bemVindo, subtitulo, cards);
    }

    private VBox criarCard(String icone, String titulo, String descricao) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefWidth(200);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);"));

        Label lblIcone = new Label(icone);
        lblIcone.setFont(Font.font("Arial", 30));

        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 16));
        lblTitulo.setTextFill(Color.web(TEXTO_ESCURO));

        Label lblDescricao = new Label(descricao);
        lblDescricao.setFont(Font.font("Arial", 12));
        lblDescricao.setTextFill(Color.web(TEXTO_ESCURO));
        lblDescricao.setWrapText(true);

        card.getChildren().addAll(lblIcone, lblTitulo, lblDescricao);
        return card;
    }

    private void mostrarGerenciarLivros() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Gerenciar Livros");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        // Botões de ação
        HBox botoesAcao = new HBox(10);
        Button btnAdicionar = new Button("Adicionar Livro");
        Button btnEditar = new Button("Editar Livro");
        Button btnRemover = new Button("Remover Livro");
        
        btnAdicionar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnEditar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnRemover.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        
        botoesAcao.getChildren().addAll(btnAdicionar, btnEditar, btnRemover);

        // Área de busca avançada
        VBox buscaAvancada = new VBox(10);
        buscaAvancada.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 10;");
        
        Label lblBusca = new Label("Busca Avançada");
        lblBusca.setFont(Font.font("Arial", 16));
        
        HBox filtros = new HBox(10);
        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Buscar por título, autor ou gênero...");
        campoBusca.setPrefWidth(300);
        
        ComboBox<String> cbGenero = new ComboBox<>();
        cbGenero.setPromptText("Gênero");
        cbGenero.getItems().addAll("Todos", "Fantasia", "Ficção Científica", "Romance", "Suspense", "Biografia");
        
        ComboBox<String> cbDisponibilidade = new ComboBox<>();
        cbDisponibilidade.setPromptText("Disponibilidade");
        cbDisponibilidade.getItems().addAll("Todos", "Disponível", "Indisponível");
        
        ComboBox<String> cbOrdenacao = new ComboBox<>();
        cbOrdenacao.setPromptText("Ordenar por");
        cbOrdenacao.getItems().addAll("Título", "Autor", "Gênero", "Disponibilidade");
        
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        
        filtros.getChildren().addAll(campoBusca, cbGenero, cbDisponibilidade, cbOrdenacao, btnBuscar);
        buscaAvancada.getChildren().addAll(lblBusca, filtros);

        // Grid de livros
        GridPane gridLivros = new GridPane();
        gridLivros.setHgap(20);
        gridLivros.setVgap(20);
        gridLivros.setPadding(new Insets(20));
        
        // Adicionar alguns livros de exemplo
        int col = 0;
        int row = 0;
        
        // Exemplo de livro 1
        VBox livro1 = criarCardLivro(
            "https://m.media-amazon.com/images/I/71jLBXtWJWL._AC_UF1000,1000_QL80_.jpg",
            "O Senhor dos Anéis",
            "J.R.R. Tolkien",
            "Fantasia",
            5
        );
        gridLivros.add(livro1, col++, row);
        
        // Exemplo de livro 2
        VBox livro2 = criarCardLivro(
            "https://m.media-amazon.com/images/I/71kxa1-0mfL._AC_UF1000,1000_QL80_.jpg",
            "1984",
            "George Orwell",
            "Ficção Científica",
            3
        );
        gridLivros.add(livro2, col++, row);
        
        // Exemplo de livro 3
        VBox livro3 = criarCardLivro(
            "https://m.media-amazon.com/images/I/71QKQ9mwV7L._AC_UF1000,1000_QL80_.jpg",
            "Dom Casmurro",
            "Machado de Assis",
            "Romance",
            2
        );
        gridLivros.add(livro3, col, row);

        ScrollPane scrollPane = new ScrollPane(gridLivros);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(500);

        contentArea.getChildren().addAll(titulo, botoesAcao, buscaAvancada, scrollPane);
    }

    private VBox criarCardLivro(String urlCapa, String titulo, String autor, String genero, int quantidade) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setPrefWidth(200);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        
        ImageView capa = new ImageView(new Image(urlCapa, 150, 200, true, true));
        capa.setPreserveRatio(true);
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 14));
        lblTitulo.setWrapText(true);
        lblTitulo.setMaxWidth(180);
        
        Label lblAutor = new Label(autor);
        lblAutor.setFont(Font.font("Arial", 12));
        lblAutor.setTextFill(Color.GRAY);
        
        Label lblGenero = new Label(genero);
        lblGenero.setFont(Font.font("Arial", 12));
        lblGenero.setTextFill(Color.web(VERMELHO));
        
        Label lblQuantidade = new Label("Disponível: " + quantidade);
        lblQuantidade.setFont(Font.font("Arial", 12));
        lblQuantidade.setTextFill(quantidade > 0 ? Color.GREEN : Color.RED);
        
        card.getChildren().addAll(capa, lblTitulo, lblAutor, lblGenero, lblQuantidade);
        
        // Efeito hover
        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);"));
        
        return card;
    }

    private void mostrarGerenciarUsuarios() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Gerenciar Usuários");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        // Botões de ação
        HBox botoesAcao = new HBox(10);
        Button btnAdicionar = new Button("Adicionar Usuário");
        Button btnEditar = new Button("Editar Usuário");
        Button btnRemover = new Button("Remover Usuário");
        
        btnAdicionar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnEditar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnRemover.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        
        botoesAcao.getChildren().addAll(btnAdicionar, btnEditar, btnRemover);

        // Campo de busca
        HBox buscaBox = new HBox(10);
        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Buscar usuários...");
        campoBusca.setPrefWidth(300);
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        buscaBox.getChildren().addAll(campoBusca, btnBuscar);

        // Lista de usuários
        ListView<String> lista = new ListView<>();
        lista.setPrefHeight(500);
        
        // Adicionar alguns usuários de exemplo
        lista.getItems().addAll(
            "João Silva - joao@email.com",
            "Maria Santos - maria@email.com",
            "Pedro Oliveira - pedro@email.com"
        );

        contentArea.getChildren().addAll(titulo, botoesAcao, buscaBox, lista);
    }

    private void mostrarGerenciarEmprestimos() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Gerenciar Empréstimos");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        // Botões de ação
        HBox botoesAcao = new HBox(10);
        Button btnNovo = new Button("Novo Empréstimo");
        Button btnDevolver = new Button("Registrar Devolução");
        Button btnRenovar = new Button("Renovar Empréstimo");
        
        btnNovo.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnDevolver.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnRenovar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        
        botoesAcao.getChildren().addAll(btnNovo, btnDevolver, btnRenovar);

        // Lista de empréstimos ativos
        Label subtitulo = new Label("Empréstimos Ativos");
        subtitulo.setFont(Font.font("Arial", 18));
        subtitulo.setTextFill(Color.web(TEXTO_ESCURO));

        ListView<String> lista = new ListView<>();
        lista.setPrefHeight(500);
        
        // Adicionar alguns empréstimos de exemplo
        lista.getItems().addAll(
            "João Silva - O Senhor dos Anéis - Devolução em: 15/04/2024",
            "Maria Santos - 1984 - Devolução em: 20/04/2024",
            "Pedro Oliveira - Dom Casmurro - Devolução em: 25/04/2024"
        );

        contentArea.getChildren().addAll(titulo, botoesAcao, subtitulo, lista);
    }

    private void mostrarRelatorios() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Relatórios");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        // Botões de relatórios
        VBox relatoriosBox = new VBox(10);
        relatoriosBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10;");
        relatoriosBox.setMaxWidth(400);

        Button btnLivrosPopulares = new Button("Livros Mais Populares");
        Button btnUsuariosAtivos = new Button("Usuários Mais Ativos");
        Button btnAtrasos = new Button("Relatório de Atrasos");
        Button btnEstatisticas = new Button("Estatísticas Gerais");

        btnLivrosPopulares.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnUsuariosAtivos.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnAtrasos.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnEstatisticas.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");

        relatoriosBox.getChildren().addAll(
            btnLivrosPopulares,
            btnUsuariosAtivos,
            btnAtrasos,
            btnEstatisticas
        );

        contentArea.getChildren().addAll(titulo, relatoriosBox);
    }

    private void mostrarPerfil() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Meu Perfil");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        VBox perfilBox = new VBox(20);
        perfilBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10;");
        perfilBox.setMaxWidth(400);

        Label lblNome = new Label("Nome: " + usuario);
        Label lblEmail = new Label("Email: admin@biblioteca.com");
        Label lblTipo = new Label("Tipo: Administrador");

        Button btnAlterarSenha = new Button("Alterar PIN");
        btnAlterarSenha.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");

        perfilBox.getChildren().addAll(lblNome, lblEmail, lblTipo, btnAlterarSenha);

        contentArea.getChildren().addAll(titulo, perfilBox);
    }

    private void voltarParaLogin() {
        new TelaLogin(stage);
    }
} 