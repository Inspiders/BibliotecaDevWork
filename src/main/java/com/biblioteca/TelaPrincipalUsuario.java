package com.biblioteca;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.fontawesome.FontAwesome;

public class TelaPrincipalUsuario {
    private static final String VERMELHO = "#E74C3C";
    private static final String VERMELHO_CLARO = "#FDEDEC";
    private static final String BRANCO = "#FFFFFF";
    private static final String TEXTO_ESCURO = "#222";
    private static final String TEXTO_CLARO = "#B3A7E6";
    private static final String AZUL_PRINCIPAL = "#1877c9";
    private static final String AZUL_CLARO = "#e8f4fd";
    private static final String AZUL_ESCURO = "#0d5aa7";
    private static final String CINZA_CLARO = "#f7fafd";
    private static final String CINZA_MEDIO = "#95A5A6";
    
    private Stage stage;
    private String usuario;
    private VBox contentArea;
    
    public TelaPrincipalUsuario(Stage primaryStage, String usuario) {
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
        sidebar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);");
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
        stage.setTitle("Biblioteca - Usuário: " + usuario);
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

        // Botões do menu com ícones Ikonli
        Button btnInicio = criarBotaoMenu("Início", MaterialDesign.MDI_HOME.getDescription());
        Button btnLivros = criarBotaoMenu("Livros Disponíveis", MaterialDesign.MDI_BOOK_OPEN_PAGE_VARIANT.getDescription());
        Button btnEmprestimos = criarBotaoMenu("Meus Empréstimos", MaterialDesign.MDI_BOOKMARK_CHECK.getDescription());
        Button btnHistorico = criarBotaoMenu("Histórico", MaterialDesign.MDI_HISTORY.getDescription());
        Button btnListaDesejos = criarBotaoMenu("Lista de Desejos", MaterialDesign.MDI_STAR_OUTLINE.getDescription());
        Button btnTags = criarBotaoMenu("Minhas Tags", MaterialDesign.MDI_TAG.getDescription());
        Button btnResenhas = criarBotaoMenu("Minhas Resenhas", MaterialDesign.MDI_PENCIL.getDescription());
        Button btnRelatorios = criarBotaoMenu("Relatórios", MaterialDesign.MDI_CHART_BAR.getDescription());
        Button btnMultas = criarBotaoMenu("Multas", MaterialDesign.MDI_CASH.getDescription());
        Button btnPerfil = criarBotaoMenu("Meu Perfil", MaterialDesign.MDI_ACCOUNT_CIRCLE.getDescription());
        Button btnSair = criarBotaoMenu("Sair", MaterialDesign.MDI_EXIT_TO_APP.getDescription());

        // Ações dos botões
        btnInicio.setOnAction(e -> mostrarTelaInicial());
        btnLivros.setOnAction(e -> mostrarLivrosDisponiveis());
        btnEmprestimos.setOnAction(e -> mostrarEmprestimos());
        btnHistorico.setOnAction(e -> mostrarHistorico());
        btnListaDesejos.setOnAction(e -> mostrarListaDesejos());
        btnTags.setOnAction(e -> mostrarTags());
        btnResenhas.setOnAction(e -> mostrarResenhas());
        btnRelatorios.setOnAction(e -> mostrarRelatorios());
        btnMultas.setOnAction(e -> mostrarMultas());
        btnPerfil.setOnAction(e -> mostrarPerfil());
        btnSair.setOnAction(e -> voltarParaLogin());

        sidebar.getChildren().addAll(
            logo, titulo,
            new Separator(),
            btnInicio, btnLivros, btnEmprestimos,
            btnHistorico, btnListaDesejos, btnTags,
            btnResenhas, btnRelatorios, btnMultas,
            btnPerfil,
            new Separator(),
            btnSair
        );

        return sidebar;
    }

    private Button criarBotaoMenu(String texto, String iconCode) {
        FontIcon icon = new FontIcon(iconCode);
        icon.setIconSize(20);
        icon.setIconColor(Color.web(BRANCO));
        Button btn = new Button(texto, icon);
        btn.setPrefWidth(Double.MAX_VALUE);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + BRANCO + "; -fx-font-size: 14px; -fx-padding: 10;");
        btn.setOnMouseEntered(e -> {
            btn.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-text-fill: " + VERMELHO + "; -fx-font-size: 14px; -fx-padding: 10;");
            icon.setIconColor(Color.web(VERMELHO));
        });
        btn.setOnMouseExited(e -> {
            btn.setStyle("-fx-background-color: transparent; -fx-text-fill: " + BRANCO + "; -fx-font-size: 14px; -fx-padding: 10;");
            icon.setIconColor(Color.web(BRANCO));
        });
        return btn;
    }

    private void mostrarTelaInicial() {
        contentArea.getChildren().clear();
        
        Label bemVindo = new Label("Bem-vindo, " + usuario + "!");
        bemVindo.setFont(Font.font("Arial", 24));
        bemVindo.setTextFill(Color.web(VERMELHO));

        Label subtitulo = new Label("O que você gostaria de fazer hoje?");
        subtitulo.setFont(Font.font("Arial", 16));
        subtitulo.setTextFill(Color.web(TEXTO_ESCURO));

        // Área de notificações
        VBox notificacoesBox = new VBox(10);
        notificacoesBox.setStyle("-fx-background-color: " + BRANCO + "; -fx-padding: 15; -fx-background-radius: 10;");
        notificacoesBox.setMaxWidth(600);
        
        Label lblNotificacoes = new Label("Notificações");
        lblNotificacoes.setFont(Font.font("Arial", 18));
        lblNotificacoes.setTextFill(Color.web(VERMELHO));
        
        // Exemplo de notificações
        VBox notificacao1 = criarNotificacao(
            "📚 Livro Disponível",
            "O livro '1984' que você reservou está disponível para empréstimo!",
            "Ver Detalhes"
        );
        
        VBox notificacao2 = criarNotificacao(
            "⚠️ Devolução Próxima",
            "O livro 'Dom Casmurro' deve ser devolvido em 2 dias.",
            "Renovar"
        );
        
        notificacoesBox.getChildren().addAll(lblNotificacoes, notificacao1, notificacao2);

        HBox cards = new HBox(20);
        cards.setAlignment(Pos.CENTER);

        // Card de Livros
        VBox cardLivros = criarCard("📚", "Livros Disponíveis", "Explore nosso acervo de livros");
        cardLivros.setOnMouseClicked(e -> mostrarLivrosDisponiveis());

        // Card de Empréstimos
        VBox cardEmprestimos = criarCard("📖", "Meus Empréstimos", "Veja seus livros emprestados");
        cardEmprestimos.setOnMouseClicked(e -> mostrarEmprestimos());

        // Card de Histórico
        VBox cardHistorico = criarCard("📋", "Histórico", "Consulte seu histórico de empréstimos");
        cardHistorico.setOnMouseClicked(e -> mostrarHistorico());

        cards.getChildren().addAll(cardLivros, cardEmprestimos, cardHistorico);

        contentArea.getChildren().addAll(bemVindo, subtitulo, notificacoesBox, cards);
    }

    private VBox criarNotificacao(String titulo, String mensagem, String acao) {
        VBox notificacao = new VBox(5);
        notificacao.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-padding: 10; -fx-background-radius: 5;");
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 14));
        lblTitulo.setTextFill(Color.web(VERMELHO));
        
        Label lblMensagem = new Label(mensagem);
        lblMensagem.setFont(Font.font("Arial", 12));
        lblMensagem.setTextFill(Color.web(TEXTO_ESCURO));
        lblMensagem.setWrapText(true);
        
        Button btnAcao = new Button(acao);
        btnAcao.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: " + BRANCO + ";");
        btnAcao.setPrefWidth(100);
        
        notificacao.getChildren().addAll(lblTitulo, lblMensagem, btnAcao);
        return notificacao;
    }

    private VBox criarCard(String icone, String titulo, String descricao) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefWidth(200);
        card.setStyle("-fx-background-color: " + BRANCO + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);");
        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: " + BRANCO + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 0);"));

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

    private void mostrarEmprestimos() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Meus Empréstimos");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(VERMELHO));

        VBox emprestimosAtivos = new VBox(10);
        emprestimosAtivos.setStyle("-fx-background-color: " + BRANCO + "; -fx-padding: 15; -fx-background-radius: 10;");
        
        Label lblAtivos = new Label("Empréstimos Ativos");
        lblAtivos.setFont(Font.font("Arial", 18));
        lblAtivos.setTextFill(Color.web(VERMELHO));

        // Lista de empréstimos
        ListView<String> lista = new ListView<>();
        lista.setPrefHeight(500);
        
        // Adicionar alguns empréstimos de exemplo
        lista.getItems().addAll(
            "O Senhor dos Anéis - Devolução em: 15/04/2024",
            "1984 - Devolução em: 20/04/2024"
        );

        emprestimosAtivos.getChildren().addAll(lblAtivos, lista);
        contentArea.getChildren().addAll(emprestimosAtivos);
    }

    private VBox criarCardEmprestimo(String titulo, String dataEmprestimo, String dataDevolucao, boolean atrasado) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-padding: 15; -fx-background-radius: 10;");
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 16));
        lblTitulo.setTextFill(Color.web(VERMELHO));
        
        Label lblEmprestimo = new Label("Emprestado em: " + dataEmprestimo);
        lblEmprestimo.setFont(Font.font("Arial", 12));
        
        Label lblDevolucao = new Label("Devolução prevista: " + dataDevolucao);
        lblDevolucao.setFont(Font.font("Arial", 12));
        
        if (atrasado) {
            Label lblAtraso = new Label("ATRASADO!");
            lblAtraso.setFont(Font.font("Arial", 12));
            lblAtraso.setTextFill(Color.web(VERMELHO));
            card.getChildren().add(lblAtraso);
        }
        
        Button btnRenovar = new Button("Renovar");
        btnRenovar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: " + BRANCO + ";");
        btnRenovar.setOnAction(e -> renovarEmprestimo(titulo));
        
        card.getChildren().addAll(lblTitulo, lblEmprestimo, lblDevolucao, btnRenovar);
        return card;
    }

    private void renovarEmprestimo(String titulo) {
        // TODO: Implementar lógica de renovação
        mostrarNotificacao("Sucesso", "Empréstimo renovado com sucesso!");
    }

    private void mostrarLivrosDisponiveis() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Livros Disponíveis");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(VERMELHO));

        // Área de busca avançada
        VBox buscaAvancada = new VBox(10);
        buscaAvancada.setStyle("-fx-background-color: " + BRANCO + "; -fx-padding: 15; -fx-background-radius: 10;");
        
        Label lblBusca = new Label("Busca Avançada");
        lblBusca.setFont(Font.font("Arial", 16));
        lblBusca.setTextFill(Color.web(VERMELHO));
        
        HBox filtros = new HBox(10);
        TextField campoBusca = new TextField();
        campoBusca.setPromptText("Buscar por título, autor ou gênero...");
        campoBusca.setPrefWidth(300);
        
        ComboBox<String> cbGenero = new ComboBox<>();
        cbGenero.setPromptText("Gênero");
        cbGenero.getItems().addAll("Todos", "Fantasia", "Ficção Científica", "Romance", "Suspense", "Biografia", "Aventura", "Drama", "Poesia", "Programação");
        
        ComboBox<String> cbDisponibilidade = new ComboBox<>();
        cbDisponibilidade.setPromptText("Disponibilidade");
        cbDisponibilidade.getItems().addAll("Todos", "Disponível", "Indisponível", "Reservado");
        
        ComboBox<String> cbOrdenacao = new ComboBox<>();
        cbOrdenacao.setPromptText("Ordenar por");
        cbOrdenacao.getItems().addAll("Título", "Autor", "Gênero", "Disponibilidade", "Avaliação");
        
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        
        filtros.getChildren().addAll(campoBusca, cbGenero, cbDisponibilidade, cbOrdenacao, btnBuscar);
        buscaAvancada.getChildren().addAll(lblBusca, filtros);

        // Área de sugestões (sem scroll)
        VBox sugestoesBox = new VBox(10);
        sugestoesBox.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 10;");
        sugestoesBox.setMaxWidth(900);
        
        Label lblSugestoes = new Label("Sugestões para Você");
        lblSugestoes.setFont(Font.font("Arial", 18));
        lblSugestoes.setTextFill(Color.web(TEXTO_ESCURO));
        
        HBox sugestoes = new HBox(20);
        sugestoes.setAlignment(Pos.CENTER_LEFT);
        sugestoes.getChildren().addAll(
            criarCardLivro(
                "https://m.media-amazon.com/images/I/81bsw6fnUiL.jpg",
                "Clean Code",
                "Robert C. Martin",
                "Programação",
                3,
                true
            ),
            criarCardLivro(
                "https://m.media-amazon.com/images/I/71aFt4+OTOL.jpg",
                "O Programador Pragmático",
                "Andrew Hunt, David Thomas",
                "Programação",
                2,
                true
            ),
            criarCardLivro(
                "https://m.media-amazon.com/images/I/81WcnNQ-TBL.jpg",
                "Java: Como Programar",
                "Paul Deitel, Harvey Deitel",
                "Programação",
                5,
                true
            )
        );
        sugestoesBox.getChildren().addAll(lblSugestoes, sugestoes);

        // Grid de livros
        GridPane gridLivros = new GridPane();
        gridLivros.setHgap(20);
        gridLivros.setVgap(20);
        gridLivros.setPadding(new Insets(20));
        int col = 0;
        int row = 0;
        // Livros famosos e de programação
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/81iqZ2HHD-L.jpg",
            "Harry Potter e a Pedra Filosofal",
            "J.K. Rowling",
            "Fantasia",
            5,
            true
        ), col++, row);
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/71UwSHSZRnS.jpg",
            "O Senhor dos Anéis",
            "J.R.R. Tolkien",
            "Fantasia",
            3,
            true
        ), col++, row);
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/81eA+4-mUIL.jpg",
            "O Hobbit",
            "J.R.R. Tolkien",
            "Fantasia",
            2,
            true
        ), col++, row);
        col = 0; row++;
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/71kxa1-0mfL.jpg",
            "1984",
            "George Orwell",
            "Ficção Científica",
            4,
            true
        ), col++, row);
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/81bsw6fnUiL.jpg",
            "Clean Code",
            "Robert C. Martin",
            "Programação",
            3,
            true
        ), col++, row);
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/71aFt4+OTOL.jpg",
            "O Programador Pragmático",
            "Andrew Hunt, David Thomas",
            "Programação",
            2,
            true
        ), col++, row);
        col = 0; row++;
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/81WcnNQ-TBL.jpg",
            "Java: Como Programar",
            "Paul Deitel, Harvey Deitel",
            "Programação",
            5,
            true
        ), col++, row);
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/81drfTT9ZfL.jpg",
            "Dom Casmurro",
            "Machado de Assis",
            "Romance",
            2,
            true
        ), col++, row);
        gridLivros.add(criarCardLivro(
            "https://m.media-amazon.com/images/I/81AFgE3E6-L.jpg",
            "O Pequeno Príncipe",
            "Antoine de Saint-Exupéry",
            "Aventura",
            3,
            true
        ), col++, row);

        ScrollPane scrollPane = new ScrollPane(gridLivros);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(500);

        contentArea.getChildren().addAll(titulo, buscaAvancada, sugestoesBox, scrollPane);
    }

    private VBox criarCardLivro(String urlCapa, String titulo, String autor, String genero, int quantidade, boolean disponivel) {
        return criarCardLivro(urlCapa, titulo, autor, genero, quantidade, disponivel, false);
    }

    private VBox criarCardLivro(String urlCapa, String titulo, String autor, String genero, int quantidade, boolean disponivel, boolean reservado) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setPrefWidth(200);
        card.setStyle("-fx-background-color: " + BRANCO + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.10), 10, 0, 0, 0); transition: box-shadow 0.3s, transform 0.3s;");
        
        ImageView capa = new ImageView(new Image(urlCapa, 120, 170, true, true));
        capa.setPreserveRatio(true);
        capa.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 8, 0, 0, 2);");
        
        // Efeito animado de hover
        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, " + VERMELHO + "55, 18, 0.2, 0, 0); transform: scale(1.04);"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: " + BRANCO + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.10), 10, 0, 0, 0); transition: box-shadow 0.3s, transform 0.3s;"));
        
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
        
        Label lblStatus = new Label();
        lblStatus.setFont(Font.font("Arial", 12));
        if (disponivel) {
            lblStatus.setText("Disponível: " + quantidade);
            lblStatus.setTextFill(Color.GREEN);
        } else if (reservado) {
            lblStatus.setText("Reservado");
            lblStatus.setTextFill(Color.ORANGE);
        } else {
            lblStatus.setText("Indisponível");
            lblStatus.setTextFill(Color.RED);
        }
        // Área de avaliação
        HBox avaliacaoBox = new HBox(5);
        avaliacaoBox.setAlignment(Pos.CENTER);
        Label lblAvaliacao = new Label("⭐ 4.5");
        lblAvaliacao.setFont(Font.font("Arial", 12));
        lblAvaliacao.setTextFill(Color.web("#F1C40F"));
        Label lblNumAvaliacoes = new Label("(128 avaliações)");
        lblNumAvaliacoes.setFont(Font.font("Arial", 10));
        lblNumAvaliacoes.setTextFill(Color.GRAY);
        avaliacaoBox.getChildren().addAll(lblAvaliacao, lblNumAvaliacoes);
        Button btnAcao = new Button();
        btnAcao.setPrefWidth(Double.MAX_VALUE);
        if (disponivel) {
            btnAcao.setText("Emprestar");
            btnAcao.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
            btnAcao.setOnAction(e -> emprestarLivro(titulo));
        } else if (reservado) {
            btnAcao.setText("Cancelar Reserva");
            btnAcao.setStyle("-fx-background-color: #95A5A6; -fx-text-fill: white;");
            btnAcao.setOnAction(e -> cancelarReserva(titulo));
        } else {
            btnAcao.setText("Reservar");
            btnAcao.setStyle("-fx-background-color: #F39C12; -fx-text-fill: white;");
            btnAcao.setOnAction(e -> reservarLivro(titulo));
        }
        Button btnAvaliar = new Button("Avaliar");
        btnAvaliar.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white;");
        btnAvaliar.setPrefWidth(Double.MAX_VALUE);
        btnAvaliar.setOnAction(e -> mostrarDialogoAvaliacao(titulo));
        card.getChildren().addAll(capa, lblTitulo, lblAutor, lblGenero, lblStatus, avaliacaoBox, btnAcao, btnAvaliar);
        return card;
    }

    private void emprestarLivro(String titulo) {
        // TODO: Implementar lógica de empréstimo
        mostrarNotificacao("Sucesso", "Livro '" + titulo + "' emprestado com sucesso!");
    }

    private void reservarLivro(String titulo) {
        // TODO: Implementar lógica de reserva
        mostrarNotificacao("Sucesso", "Livro '" + titulo + "' reservado com sucesso! Você será notificado quando estiver disponível.");
    }

    private void cancelarReserva(String titulo) {
        // TODO: Implementar lógica de cancelamento de reserva
        mostrarNotificacao("Sucesso", "Reserva do livro '" + titulo + "' cancelada com sucesso!");
    }

    private void mostrarNotificacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarHistorico() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Histórico de Empréstimos");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(VERMELHO));

        VBox historicoBox = new VBox(10);
        historicoBox.setStyle("-fx-background-color: " + BRANCO + "; -fx-padding: 15; -fx-background-radius: 10;");
        
        Label lblHistorico = new Label("Histórico Completo");
        lblHistorico.setFont(Font.font("Arial", 18));
        lblHistorico.setTextFill(Color.web(VERMELHO));

        // Lista de histórico
        ListView<String> lista = new ListView<>();
        lista.setPrefHeight(500);
        
        // Adicionar alguns itens de exemplo
        lista.getItems().addAll(
            "O Senhor dos Anéis - Devolvido em: 01/03/2024",
            "1984 - Devolvido em: 15/02/2024",
            "Dom Casmurro - Devolvido em: 01/02/2024"
        );

        historicoBox.getChildren().addAll(lblHistorico, lista);
        contentArea.getChildren().addAll(titulo, historicoBox);
    }

    private void mostrarPerfil() {
        contentArea.getChildren().clear();

        // Capa vermelha estilo LinkedIn
        VBox capa = new VBox();
        capa.setStyle("-fx-background-color: " + VERMELHO + "; -fx-min-height: 120px; -fx-background-radius: 16 16 0 0;");
        capa.setMinHeight(120);
        capa.setMaxWidth(600);

        // Avatar circular angolano
        ImageView avatar = new ImageView(new Image("https://randomuser.me/api/portraits/men/76.jpg", 110, 110, true, true));
        avatar.setStyle("-fx-background-radius: 55; -fx-effect: dropshadow(gaussian, " + VERMELHO + "AA, 8, 0.2, 0, 0);");
        avatar.setClip(new javafx.scene.shape.Circle(55, 55, 55));
        avatar.setTranslateY(-55);
        
        Button btnUpload = new Button("Alterar Foto");
        btnUpload.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: " + BRANCO + ";");
        btnUpload.setOnAction(e -> mostrarDialogoUploadFoto(avatar));
        btnUpload.setTranslateY(-45);

        // Card principal do perfil
        VBox perfilBox = new VBox(10);
        perfilBox.setStyle("-fx-background-color: " + BRANCO + "; -fx-padding: 30 30 20 30; -fx-background-radius: 0 0 16 16; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 0);");
        perfilBox.setMaxWidth(600);
        perfilBox.setAlignment(Pos.CENTER);
        perfilBox.setTranslateY(-60);

        // Nome, email, localização
        Label lblNome = new Label("Adelino Pedro");
        lblNome.setFont(Font.font("Arial", 22));
        lblNome.setTextFill(Color.web(VERMELHO));
        Label lblEmail = new Label("adelino.pedro@exemplo.co.ao");
        lblEmail.setFont(Font.font("Arial", 14));
        lblEmail.setTextFill(Color.GRAY);
        Label lblLocal = new Label("Luanda, Angola");
        lblLocal.setFont(Font.font("Arial", 13));
        lblLocal.setTextFill(Color.web(VERMELHO));

        // Classificação
        HBox classificacaoBox = new HBox(8);
        classificacaoBox.setAlignment(Pos.CENTER);
        Label lblNivel = new Label("Leitor Ouro");
        lblNivel.setFont(Font.font("Arial", 14));
        lblNivel.setTextFill(Color.web("#F1C40F"));
        Label lblEstrelas = new Label("⭐⭐⭐⭐⭐");
        lblEstrelas.setFont(Font.font("Arial", 18));
        lblEstrelas.setTextFill(Color.web("#F1C40F"));
        classificacaoBox.getChildren().addAll(lblNivel, lblEstrelas);

        // Estatísticas em cards
        HBox stats = new HBox(30);
        stats.setAlignment(Pos.CENTER);
        VBox stat1 = criarStatCard("Livros Lidos", "24");
        VBox stat2 = criarStatCard("Resenhas", "12");
        VBox stat3 = criarStatCard("Tags Favoritas", "Fantasia, Programação");
        VBox stat4 = criarStatCard("Multas Pagas", "5.000 Kz");
        stats.getChildren().addAll(stat1, stat2, stat3, stat4);

        Button btnEditar = new Button("Editar Perfil");
        btnEditar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white; -fx-font-weight: bold;");
        Button btnAlterarSenha = new Button("Alterar PIN");
        btnAlterarSenha.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");

        perfilBox.getChildren().addAll(lblNome, lblEmail, lblLocal, classificacaoBox, stats, btnEditar, btnAlterarSenha);
        contentArea.getChildren().addAll(capa, avatar, btnUpload, perfilBox);
    }

    private VBox criarStatCard(String titulo, String valor) {
        VBox stat = new VBox(2);
        stat.setAlignment(Pos.CENTER);
        stat.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-padding: 12 18; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(231,76,60,0.08), 4, 0, 0, 0);");
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 12));
        lblTitulo.setTextFill(Color.GRAY);
        Label lblValor = new Label(valor);
        lblValor.setFont(Font.font("Arial", 16));
        lblValor.setTextFill(Color.web(VERMELHO));
        stat.getChildren().addAll(lblTitulo, lblValor);
        return stat;
    }

    private void mostrarDialogoUploadFoto(ImageView avatar) {
        // Simulação de upload de foto (poderia abrir FileChooser real)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Upload de Foto");
        alert.setHeaderText(null);
        alert.setContentText("Funcionalidade de upload de foto em desenvolvimento!\nNo futuro, você poderá escolher uma imagem do seu computador.");
        alert.showAndWait();
    }

    private void voltarParaLogin() {
        new TelaLogin(stage);
    }

    private void mostrarDialogoAvaliacao(String titulo) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Avaliar Livro");
        dialog.setHeaderText("Avalie o livro: " + titulo);

        // Criar conteúdo do diálogo
        VBox content = new VBox(10);
        content.setPadding(new Insets(20));

        // Estrelas de avaliação
        HBox estrelasBox = new HBox(5);
        estrelasBox.setAlignment(Pos.CENTER);
        
        Label lblEstrelas = new Label("Sua avaliação:");
        lblEstrelas.setFont(Font.font("Arial", 14));
        
        ToggleGroup grupoEstrelas = new ToggleGroup();
        HBox estrelas = new HBox(5);
        for (int i = 1; i <= 5; i++) {
            final int rating = i;
            RadioButton estrela = new RadioButton("⭐");
            estrela.setToggleGroup(grupoEstrelas);
            estrela.setFont(Font.font("Arial", 20));
            estrela.setTextFill(Color.web("#F1C40F"));
            estrela.setOnAction(e -> estrela.setTextFill(Color.web("#F1C40F")));
            estrelas.getChildren().add(estrela);
        }
        
        estrelasBox.getChildren().addAll(lblEstrelas, estrelas);

        // Campo de comentário
        Label lblComentario = new Label("Seu comentário:");
        lblComentario.setFont(Font.font("Arial", 14));
        
        TextArea comentario = new TextArea();
        comentario.setPromptText("Compartilhe sua opinião sobre o livro...");
        comentario.setPrefRowCount(3);
        comentario.setWrapText(true);

        content.getChildren().addAll(estrelasBox, lblComentario, comentario);

        // Botões
        ButtonType btnEnviar = new ButtonType("Enviar", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnEnviar, btnCancelar);

        dialog.getDialogPane().setContent(content);

        // Ação ao enviar
        dialog.setResultConverter(buttonType -> {
            if (buttonType == btnEnviar) {
                RadioButton estrelaSelecionada = (RadioButton) grupoEstrelas.getSelectedToggle();
                if (estrelaSelecionada != null) {
                    int avaliacao = estrelas.getChildren().indexOf(estrelaSelecionada) + 1;
                    String comentarioTexto = comentario.getText();
                    salvarAvaliacao(titulo, avaliacao, comentarioTexto);
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    private void salvarAvaliacao(String titulo, int avaliacao, String comentario) {
        // TODO: Implementar lógica de salvamento da avaliação
        mostrarNotificacao("Sucesso", "Sua avaliação foi enviada com sucesso!");
    }

    private void mostrarListaDesejos() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Minha Lista de Desejos");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        // Grid de livros desejados
        GridPane gridLivros = new GridPane();
        gridLivros.setHgap(20);
        gridLivros.setVgap(20);
        gridLivros.setPadding(new Insets(20));
        
        // Exemplo de livros na lista de desejos
        int col = 0;
        int row = 0;
        
        gridLivros.add(criarCardLivroDesejado(
            "https://m.media-amazon.com/images/I/71QKQ9mwV7L._AC_UF1000,1000_QL80_.jpg",
            "O Nome do Vento",
            "Patrick Rothfuss",
            "Fantasia",
            "Adicionado em: 15/03/2024"
        ), col++, row);
        
        gridLivros.add(criarCardLivroDesejado(
            "https://m.media-amazon.com/images/I/71QKQ9mwV7L._AC_UF1000,1000_QL80_.jpg",
            "A Roda do Tempo",
            "Robert Jordan",
            "Fantasia",
            "Adicionado em: 10/03/2024"
        ), col, row);

        ScrollPane scrollPane = new ScrollPane(gridLivros);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(500);

        contentArea.getChildren().addAll(titulo, scrollPane);
    }

    private VBox criarCardLivroDesejado(String urlCapa, String titulo, String autor, String genero, String dataAdicao) {
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
        
        Label lblData = new Label(dataAdicao);
        lblData.setFont(Font.font("Arial", 10));
        lblData.setTextFill(Color.GRAY);
        
        Button btnRemover = new Button("Remover da Lista");
        btnRemover.setStyle("-fx-background-color: #95A5A6; -fx-text-fill: white;");
        btnRemover.setPrefWidth(Double.MAX_VALUE);
        btnRemover.setOnAction(e -> removerDaListaDesejos(titulo));
        
        card.getChildren().addAll(capa, lblTitulo, lblAutor, lblGenero, lblData, btnRemover);
        
        return card;
    }

    private void mostrarTags() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Minhas Tags");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        VBox tagsBox = new VBox(20);
        tagsBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10;");
        tagsBox.setMaxWidth(600);

        // Área de adicionar nova tag
        HBox addTagBox = new HBox(10);
        TextField campoTag = new TextField();
        campoTag.setPromptText("Nova tag...");
        campoTag.setPrefWidth(200);
        
        Button btnAdicionar = new Button("Adicionar Tag");
        btnAdicionar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnAdicionar.setOnAction(e -> adicionarTag(campoTag.getText()));
        
        addTagBox.getChildren().addAll(campoTag, btnAdicionar);

        // Lista de tags existentes
        FlowPane tagsFlow = new FlowPane();
        tagsFlow.setHgap(10);
        tagsFlow.setVgap(10);
        
        // Exemplo de tags
        String[] tagsExemplo = {"Fantasia", "Ficção Científica", "Romance", "Suspense", "Biografia", "Aventura"};
        for (String tag : tagsExemplo) {
            tagsFlow.getChildren().add(criarTagChip(tag));
        }

        tagsBox.getChildren().addAll(addTagBox, tagsFlow);
        contentArea.getChildren().addAll(titulo, tagsBox);
    }

    private HBox criarTagChip(String tag) {
        HBox chip = new HBox(5);
        chip.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-padding: 5 10; -fx-background-radius: 15;");
        chip.setAlignment(Pos.CENTER);
        
        Label lblTag = new Label(tag);
        lblTag.setFont(Font.font("Arial", 12));
        lblTag.setTextFill(Color.web(VERMELHO));
        
        Button btnRemover = new Button("×");
        btnRemover.setStyle("-fx-background-color: transparent; -fx-text-fill: " + VERMELHO + ";");
        btnRemover.setOnAction(e -> removerTag(tag));
        
        chip.getChildren().addAll(lblTag, btnRemover);
        return chip;
    }

    private void mostrarResenhas() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Minhas Resenhas");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        VBox resenhasBox = new VBox(20);
        resenhasBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10;");
        resenhasBox.setMaxWidth(800);

        // Exemplo de resenhas
        resenhasBox.getChildren().addAll(
            criarCardResenha(
                "O Senhor dos Anéis",
                "J.R.R. Tolkien",
                "Uma obra-prima da fantasia que mudou o gênero para sempre. A jornada de Frodo é emocionante e cheia de significado.",
                "15/03/2024",
                5
            ),
            criarCardResenha(
                "1984",
                "George Orwell",
                "Uma distopia perturbadora que continua relevante nos dias de hoje. A crítica social é profunda e necessária.",
                "10/03/2024",
                4
            )
        );

        contentArea.getChildren().addAll(titulo, resenhasBox);
    }

    private VBox criarCardResenha(String titulo, String autor, String conteudo, String data, int avaliacao) {
        VBox card = new VBox(10);
        card.setStyle("-fx-background-color: " + VERMELHO_CLARO + "; -fx-padding: 15; -fx-background-radius: 10;");
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 16));
        lblTitulo.setTextFill(Color.web(VERMELHO));
        
        Label lblAutor = new Label("por " + autor);
        lblAutor.setFont(Font.font("Arial", 12));
        lblAutor.setTextFill(Color.GRAY);
        
        Label lblConteudo = new Label(conteudo);
        lblConteudo.setFont(Font.font("Arial", 12));
        lblConteudo.setWrapText(true);
        
        HBox footer = new HBox(20);
        footer.setAlignment(Pos.CENTER_LEFT);
        
        Label lblData = new Label(data);
        lblData.setFont(Font.font("Arial", 10));
        lblData.setTextFill(Color.GRAY);
        
        Label lblAvaliacao = new Label("⭐".repeat(avaliacao));
        lblAvaliacao.setFont(Font.font("Arial", 12));
        lblAvaliacao.setTextFill(Color.web("#F1C40F"));
        
        Button btnCompartilhar = new Button("Compartilhar");
        btnCompartilhar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnCompartilhar.setOnAction(e -> compartilharResenha(titulo));
        
        footer.getChildren().addAll(lblData, lblAvaliacao, btnCompartilhar);
        
        card.getChildren().addAll(lblTitulo, lblAutor, lblConteudo, footer);
        return card;
    }

    private void mostrarRelatorios() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Relatórios");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        VBox relatoriosBox = new VBox(20);
        relatoriosBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10;");
        relatoriosBox.setMaxWidth(800);

        // Estatísticas gerais
        GridPane stats = new GridPane();
        stats.setHgap(20);
        stats.setVgap(20);
        
        stats.add(criarCardEstatistica("Livros Lidos", "24", "📚"), 0, 0);
        stats.add(criarCardEstatistica("Empréstimos Ativos", "3", "📖"), 1, 0);
        stats.add(criarCardEstatistica("Resenhas", "12", "✍️"), 2, 0);
        stats.add(criarCardEstatistica("Gênero Favorito", "Fantasia", "🏆"), 0, 1);
        stats.add(criarCardEstatistica("Autor Favorito", "J.R.R. Tolkien", "👤"), 1, 1);
        stats.add(criarCardEstatistica("Média de Leitura", "2/mês", "📊"), 2, 1);

        // Botões de exportação
        HBox botoesExportacao = new HBox(10);
        botoesExportacao.setAlignment(Pos.CENTER);
        
        Button btnPDF = new Button("Exportar PDF");
        btnPDF.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnPDF.setStyle("-fx-background-color: " + AZUL_PRINCIPAL + "; -fx-text-fill: white;");
        btnPDF.setOnAction(e -> exportarRelatorio("PDF"));
        
        Button btnCSV = new Button("Exportar CSV");
        btnCSV.setStyle("-fx-background-color: " + AZUL_PRINCIPAL + "; -fx-text-fill: white;");
        btnCSV.setOnAction(e -> exportarRelatorio("CSV"));
        
        botoesExportacao.getChildren().addAll(btnPDF, btnCSV);

        relatoriosBox.getChildren().addAll(stats, botoesExportacao);
        contentArea.getChildren().addAll(titulo, relatoriosBox);
    }

    private VBox criarCardEstatistica(String titulo, String valor, String icone) {
        VBox card = new VBox(5);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: " + AZUL_CLARO + "; -fx-background-radius: 10;");
        
        Label lblIcone = new Label(icone);
        lblIcone.setFont(Font.font("Arial", 24));
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 12));
        lblTitulo.setTextFill(Color.GRAY);
        
        Label lblValor = new Label(valor);
        lblValor.setFont(Font.font("Arial", 20));
        lblValor.setTextFill(Color.web(AZUL_PRINCIPAL));
        
        card.getChildren().addAll(lblIcone, lblTitulo, lblValor);
        return card;
    }

    private void mostrarMultas() {
        contentArea.getChildren().clear();
        
        Label titulo = new Label("Minhas Multas");
        titulo.setFont(Font.font("Arial", 24));
        titulo.setTextFill(Color.web(TEXTO_ESCURO));

        VBox multasBox = new VBox(20);
        multasBox.setStyle("-fx-background-color: white; -fx-padding: 20; -fx-background-radius: 10;");
        multasBox.setMaxWidth(800);

        // Resumo de multas
        HBox resumo = new HBox(20);
        resumo.setAlignment(Pos.CENTER);
        
        VBox totalMultas = criarCardMulta("Total em Multas", "R$ 15,00", "💰");
        VBox multasPendentes = criarCardMulta("Multas Pendentes", "2", "⚠️");
        VBox multasPagas = criarCardMulta("Multas Pagas", "3", "✅");
        
        resumo.getChildren().addAll(totalMultas, multasPendentes, multasPagas);

        // Lista de multas
        VBox listaMultas = new VBox(10);
        listaMultas.getChildren().addAll(
            criarItemMulta(
                "O Senhor dos Anéis",
                "Atraso na devolução",
                "R$ 5,00",
                "15/03/2024",
                true
            ),
            criarItemMulta(
                "1984",
                "Atraso na devolução",
                "R$ 10,00",
                "10/03/2024",
                false
            )
        );

        multasBox.getChildren().addAll(resumo, listaMultas);
        contentArea.getChildren().addAll(titulo, multasBox);
    }

    private VBox criarCardMulta(String titulo, String valor, String icone) {
        VBox card = new VBox(5);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: " + AZUL_CLARO + "; -fx-background-radius: 10;");
        
        Label lblIcone = new Label(icone);
        lblIcone.setFont(Font.font("Arial", 24));
        
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 12));
        lblTitulo.setTextFill(Color.GRAY);
        
        Label lblValor = new Label(valor);
        lblValor.setFont(Font.font("Arial", 20));
        lblValor.setTextFill(Color.web(AZUL_PRINCIPAL));
        
        card.getChildren().addAll(lblIcone, lblTitulo, lblValor);
        return card;
    }

    private HBox criarItemMulta(String livro, String motivo, String valor, String data, boolean pendente) {
        HBox item = new HBox(20);
        item.setStyle("-fx-background-color: " + AZUL_CLARO + "; -fx-padding: 15; -fx-background-radius: 10;");
        item.setAlignment(Pos.CENTER_LEFT);
        
        Label lblLivro = new Label(livro);
        lblLivro.setFont(Font.font("Arial", 14));
        lblLivro.setTextFill(Color.web(TEXTO_ESCURO));
        
        Label lblMotivo = new Label(motivo);
        lblMotivo.setFont(Font.font("Arial", 12));
        lblMotivo.setTextFill(Color.GRAY);
        
        Label lblValor = new Label(valor);
        lblValor.setFont(Font.font("Arial", 14));
        lblValor.setTextFill(Color.web(AZUL_PRINCIPAL));
        
        Label lblData = new Label(data);
        lblData.setFont(Font.font("Arial", 12));
        lblData.setTextFill(Color.GRAY);
        
        Button btnAcao = new Button(pendente ? "Pagar" : "Pago");
        btnAcao.setStyle("-fx-background-color: " + (pendente ? AZUL_PRINCIPAL : "#27AE60") + "; -fx-text-fill: white;");
        btnAcao.setOnAction(e -> pagarMulta(livro));
        
        item.getChildren().addAll(lblLivro, lblMotivo, lblValor, lblData, btnAcao);
        return item;
    }

    // Métodos auxiliares para as novas funcionalidades
    private void removerDaListaDesejos(String titulo) {
        // TODO: Implementar lógica de remoção
        mostrarNotificacao("Sucesso", "Livro removido da lista de desejos!");
    }

    private void adicionarTag(String tag) {
        // TODO: Implementar lógica de adição
        mostrarNotificacao("Sucesso", "Tag adicionada com sucesso!");
    }

    private void removerTag(String tag) {
        // TODO: Implementar lógica de remoção
        mostrarNotificacao("Sucesso", "Tag removida com sucesso!");
    }

    private void compartilharResenha(String titulo) {
        // TODO: Implementar lógica de compartilhamento
        mostrarNotificacao("Sucesso", "Resenha compartilhada com sucesso!");
    }

    private void exportarRelatorio(String formato) {
        // TODO: Implementar lógica de exportação
        mostrarNotificacao("Sucesso", "Relatório exportado em " + formato + " com sucesso!");
    }

    private void pagarMulta(String livro) {
        // TODO: Implementar lógica de pagamento
        mostrarNotificacao("Sucesso", "Multa paga com sucesso!");
    }
} 