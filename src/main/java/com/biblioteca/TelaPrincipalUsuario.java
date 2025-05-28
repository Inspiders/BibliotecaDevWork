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
import javafx.scene.control.PasswordField;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.fontawesome.FontAwesome;

import java.util.ArrayList;
import java.util.List;

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
        
        // Feed de novidades/atividades
        VBox feedBox = new VBox(8);
        feedBox.setStyle("-fx-background-color: white; -fx-padding: 18 24 18 24; -fx-background-radius: 10;");
        feedBox.setMaxWidth(700);
        Label lblFeed = new Label("Feed de Novidades");
        lblFeed.setFont(Font.font("Arial", 18));
        lblFeed.setTextFill(Color.web(VERMELHO));
        feedBox.getChildren().addAll(
            lblFeed,
            criarFeedItem("Novo livro adicionado: 'O Alquimista' de Paulo Coelho."),
            criarFeedItem("Você devolveu 'Clean Code' em 10/04/2024."),
            criarFeedItem("Promoção: Leia 5 livros este mês e ganhe um brinde!"),
            criarFeedItem("Novo comentário na sua resenha de '1984'.")
        );

        Label bemVindo = new Label("Bem-vindo, " + usuario + "!");
        bemVindo.setFont(Font.font("Arial", 24));
        bemVindo.setTextFill(Color.web(VERMELHO));

        Label subtitulo = new Label("O que você gostaria de fazer hoje?");
        subtitulo.setFont(Font.font("Arial", 16));
        subtitulo.setTextFill(Color.web(TEXTO_ESCURO));

        contentArea.getChildren().addAll(feedBox, bemVindo, subtitulo);
    }

    private HBox criarFeedItem(String texto) {
        HBox item = new HBox();
        item.setSpacing(8);
        item.setAlignment(Pos.CENTER_LEFT);
        Label icone = new Label("📰");
        icone.setFont(Font.font("Arial", 16));
        Label lblTexto = new Label(texto);
        lblTexto.setFont(Font.font("Arial", 13));
        lblTexto.setTextFill(Color.web(TEXTO_ESCURO));
        item.getChildren().addAll(icone, lblTexto);
        return item;
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
        buscaAvancada.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-background-radius: 10;");
        
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

        // Seção: Recomendados para Você
        Label lblRecomendados = new Label("Recomendados para Você");
        lblRecomendados.setFont(Font.font("Arial", 18));
        lblRecomendados.setTextFill(Color.web(VERMELHO));
        HBox recomendados = new HBox(24);
        recomendados.setAlignment(Pos.CENTER_LEFT);
        recomendados.getChildren().addAll(
            criarLivroLinha("https://m.media-amazon.com/images/I/81iqZ2HHD-L.jpg", "Harry Potter e a Pedra Filosofal", "J.K. Rowling", "Fantasia", 5, true, 4.8, 320),
            criarLivroLinha("https://m.media-amazon.com/images/I/71UwSHSZRnS.jpg", "O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 3, true, 4.9, 410),
            criarLivroLinha("https://m.media-amazon.com/images/I/81eA+4-mUIL.jpg", "O Hobbit", "J.R.R. Tolkien", "Fantasia", 2, true, 4.7, 210)
        );

        // Seção: Programação
        Label lblProg = new Label("Programação");
        lblProg.setFont(Font.font("Arial", 18));
        lblProg.setTextFill(Color.web(VERMELHO));
        HBox prog = new HBox(24);
        prog.setAlignment(Pos.CENTER_LEFT);
        prog.getChildren().addAll(
            criarLivroLinha("https://m.media-amazon.com/images/I/81bsw6fnUiL.jpg", "Clean Code", "Robert C. Martin", "Programação", 3, true, 4.5, 128),
            criarLivroLinha("https://m.media-amazon.com/images/I/71aFt4+OTOL.jpg", "O Programador Pragmático", "Andrew Hunt, David Thomas", "Programação", 2, true, 4.6, 98),
            criarLivroLinha("https://m.media-amazon.com/images/I/81WcnNQ-TBL.jpg", "Java: Como Programar", "Paul Deitel, Harvey Deitel", "Programação", 5, true, 4.4, 75)
        );

        // Seção: Romance
        Label lblRomance = new Label("Romance");
        lblRomance.setFont(Font.font("Arial", 18));
        lblRomance.setTextFill(Color.web(VERMELHO));
        HBox romance = new HBox(24);
        romance.setAlignment(Pos.CENTER_LEFT);
        romance.getChildren().addAll(
            criarLivroLinha("https://m.media-amazon.com/images/I/81drfTT9ZfL.jpg", "Dom Casmurro", "Machado de Assis", "Romance", 2, true, 4.2, 60),
            criarLivroLinha("https://m.media-amazon.com/images/I/81AFgE3E6-L.jpg", "O Pequeno Príncipe", "Antoine de Saint-Exupéry", "Aventura", 3, true, 4.8, 200)
        );

        // Seção: Ficção Científica
        Label lblFiccao = new Label("Ficção Científica");
        lblFiccao.setFont(Font.font("Arial", 18));
        lblFiccao.setTextFill(Color.web(VERMELHO));
        HBox ficcao = new HBox(24);
        ficcao.setAlignment(Pos.CENTER_LEFT);
        ficcao.getChildren().addAll(
            criarLivroLinha("https://m.media-amazon.com/images/I/71kxa1-0mfL.jpg", "1984", "George Orwell", "Ficção Científica", 4, true, 4.7, 180)
        );

        contentArea.getChildren().addAll(titulo, buscaAvancada, lblRecomendados, recomendados, lblProg, prog, lblRomance, romance, lblFiccao, ficcao);
    }

    private VBox criarLivroLinha(String urlCapa, String titulo, String autor, String genero, int quantidade, boolean disponivel, double avaliacao, int numAvaliacoes) {
        VBox box = new VBox(6);
        box.setAlignment(Pos.TOP_CENTER);
        box.setPrefWidth(170);
        box.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 0); transition: box-shadow 0.3s, transform 0.3s;");
        box.setPadding(new Insets(10, 8, 10, 8));
        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #FDEDEC; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, #E74C3C55, 16, 0.2, 0, 0); transform: scale(1.04);"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 8, 0, 0, 0); transition: box-shadow 0.3s, transform 0.3s;"));
        ImageView capa = new ImageView(new Image(urlCapa, 110, 150, true, true));
        capa.setPreserveRatio(true);
        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", 13));
        lblTitulo.setWrapText(true);
        lblTitulo.setMaxWidth(140);
        Label lblAutor = new Label(autor);
        lblAutor.setFont(Font.font("Arial", 11));
        lblAutor.setTextFill(Color.GRAY);
        Label lblGenero = new Label(genero);
        lblGenero.setFont(Font.font("Arial", 11));
        lblGenero.setTextFill(Color.web(VERMELHO));
        Label lblStatus = new Label("Disponível: " + quantidade);
        lblStatus.setFont(Font.font("Arial", 11));
        lblStatus.setTextFill(Color.web("#27AE60"));
        // Avaliação
        HBox avaliacaoBox = new HBox(3);
        avaliacaoBox.setAlignment(Pos.CENTER);
        Label lblEstrelas = new Label("★".repeat((int) Math.round(avaliacao)));
        lblEstrelas.setFont(Font.font("Arial", 12));
        lblEstrelas.setTextFill(Color.web("#F1C40F"));
        Label lblNumAval = new Label(String.format("%.1f (%d)", avaliacao, numAvaliacoes));
        lblNumAval.setFont(Font.font("Arial", 10));
        lblNumAval.setTextFill(Color.GRAY);
        avaliacaoBox.getChildren().addAll(lblEstrelas, lblNumAval);
        HBox botoes = new HBox(6);
        botoes.setAlignment(Pos.CENTER);
        Button btnEmprestar = new Button("Emprestar");
        btnEmprestar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white; -fx-font-size: 11px; -fx-background-radius: 8;");
        btnEmprestar.setPrefWidth(70);
        btnEmprestar.setOnAction(e -> emprestarLivro(titulo));
        Button btnAvaliar = new Button("Avaliar");
        btnAvaliar.setStyle("-fx-background-color: #3498DB; -fx-text-fill: white; -fx-font-size: 11px; -fx-background-radius: 8;");
        btnAvaliar.setPrefWidth(70);
        btnAvaliar.setOnAction(e -> mostrarDialogoAvaliacao(titulo));
        botoes.getChildren().addAll(btnEmprestar, btnAvaliar);
        box.getChildren().addAll(capa, lblTitulo, lblAutor, lblGenero, lblStatus, avaliacaoBox, botoes);
        return box;
    }

    private void emprestarLivro(String titulo) {
        // TODO: Implementar lógica de empréstimo
        mostrarNotificacao("Sucesso", "Livro '" + titulo + "' emprestado com sucesso!");
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

        // Container responsivo centralizado
        VBox container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.setSpacing(0);
        container.setFillWidth(false);
        container.setPrefWidth(600);
        container.setMaxWidth(700);
        container.setMinWidth(350);
        container.setStyle("-fx-background-color: transparent;");

        // Capa vermelha responsiva
        StackPane capa = new StackPane();
        capa.setStyle("-fx-background-color: " + VERMELHO + "; -fx-background-radius: 18 18 0 0;");
        capa.setMinHeight(140);
        capa.setMaxWidth(700);

        // Avatar destacado
        StackPane avatarPane = new StackPane();
        avatarPane.setPrefHeight(0);
        avatarPane.setTranslateY(-55);
        ImageView avatar = new ImageView(new Image("https://avataaars.io/?avatarStyle=Circle&topType=ShortHairShortCurly&accessoriesType=Blank&hairColor=Black&facialHairType=BeardLight&facialHairColor=Black&clotheType=BlazerShirt&eyeType=Default&eyebrowType=Default&mouthType=Smile&skinColor=Brown", 110, 110, true, true));
        avatar.setClip(new javafx.scene.shape.Circle(55, 55, 55));
        avatar.setStyle("-fx-effect: dropshadow(gaussian, #00000033, 8, 0.2, 0, 0);");
        Circle borda = new Circle(55, 55, 56, Color.WHITE);
        borda.setStroke(Color.WHITE);
        borda.setStrokeWidth(4);
        avatarPane.getChildren().addAll(borda, avatar);

        // Botão de upload de foto
        Button btnUpload = new Button("Alterar Foto");
        btnUpload.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: " + BRANCO + "; -fx-font-weight: bold;");
        btnUpload.setOnAction(e -> mostrarDialogoUploadFoto(avatar));
        btnUpload.setTranslateY(-40);

        // Card principal do perfil
        VBox perfilBox = new VBox(16);
        perfilBox.setStyle("-fx-background-color: " + BRANCO + "; -fx-padding: 36 32 24 32; -fx-background-radius: 0 0 18 18; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 0);");
        perfilBox.setMaxWidth(700);
        perfilBox.setAlignment(Pos.CENTER);
        perfilBox.setTranslateY(-60);

        // Nome, email, localização
        Label lblNome = new Label("Adelino Pedro");
        lblNome.setFont(Font.font("Arial", 26));
        lblNome.setTextFill(Color.web(VERMELHO));
        Label lblEmail = new Label("adelino.pedro@exemplo.co.ao");
        lblEmail.setFont(Font.font("Arial", 15));
        lblEmail.setTextFill(Color.GRAY);
        Label lblLocal = new Label("Luanda, Angola");
        lblLocal.setFont(Font.font("Arial", 14));
        lblLocal.setTextFill(Color.web(VERMELHO));

        // Classificação
        HBox classificacaoBox = new HBox(8);
        classificacaoBox.setAlignment(Pos.CENTER);
        Label lblNivel = new Label("Leitor Ouro");
        lblNivel.setFont(Font.font("Arial", 15));
        lblNivel.setTextFill(Color.web("#F1C40F"));
        Label lblEstrelas = new Label("⭐⭐⭐⭐⭐");
        lblEstrelas.setFont(Font.font("Arial", 20));
        lblEstrelas.setTextFill(Color.web("#F1C40F"));
        classificacaoBox.getChildren().addAll(lblNivel, lblEstrelas);

        // Estatísticas em grid responsivo
        GridPane stats = new GridPane();
        stats.setHgap(24);
        stats.setVgap(16);
        stats.setAlignment(Pos.CENTER);
        stats.add(criarStatCard("Livros Lidos", "24"), 0, 0);
        stats.add(criarStatCard("Resenhas", "12"), 1, 0);
        stats.add(criarStatCard("Tags Favoritas", "Fantasia, Programação"), 0, 1);
        stats.add(criarStatCard("Multas Pagas", "5.000 Kz"), 1, 1);

        // Botões de ação responsivos
        HBox botoes = new HBox(16);
        botoes.setAlignment(Pos.CENTER);
        Button btnEditar = new Button("Editar Perfil");
        btnEditar.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white; -fx-font-weight: bold;");
        Button btnAlterarSenha = new Button("Alterar PIN");
        btnAlterarSenha.setStyle("-fx-background-color: " + VERMELHO + "; -fx-text-fill: white;");
        btnAlterarSenha.setOnAction(e -> mostrarDialogoAlterarPIN());
        botoes.getChildren().addAll(btnEditar, btnAlterarSenha);

        perfilBox.getChildren().addAll(lblNome, lblEmail, lblLocal, classificacaoBox, stats, botoes);
        container.getChildren().addAll(capa, avatarPane, btnUpload, perfilBox);
        contentArea.getChildren().add(container);
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

        VBox content = new VBox(14);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        // Estrelas de avaliação (clicáveis e animadas)
        HBox estrelasBox = new HBox(6);
        estrelasBox.setAlignment(Pos.CENTER);
        Label lblEstrelas = new Label("Sua avaliação:");
        lblEstrelas.setFont(Font.font("Arial", 14));
        HBox estrelas = new HBox(2);
        estrelas.setAlignment(Pos.CENTER);
        ToggleGroup grupoEstrelas = new ToggleGroup();
        List<RadioButton> estrelasList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            RadioButton estrela = new RadioButton("★");
            estrela.setToggleGroup(grupoEstrelas);
            estrela.setFont(Font.font("Arial", 24));
            estrela.setTextFill(Color.web("#B3A7E6"));
            final int rating = i;
            estrela.setOnAction(e -> {
                for (int j = 0; j < 5; j++) {
                    estrelasList.get(j).setTextFill(j < rating ? Color.web("#F1C40F") : Color.web("#B3A7E6"));
                }
            });
            estrelasList.add(estrela);
            estrelas.getChildren().add(estrela);
        }
        estrelasBox.getChildren().addAll(lblEstrelas, estrelas);

        // Campo de comentário estilizado
        Label lblComentario = new Label("Seu comentário:");
        lblComentario.setFont(Font.font("Arial", 14));
        TextArea comentario = new TextArea();
        comentario.setPromptText("Compartilhe sua opinião sobre o livro...");
        comentario.setPrefRowCount(3);
        comentario.setWrapText(true);
        comentario.setStyle("-fx-background-radius: 8; -fx-border-radius: 8; -fx-border-color: #E74C3C;");

        content.getChildren().addAll(estrelasBox, lblComentario, comentario);

        // Botões
        ButtonType btnEnviar = new ButtonType("Enviar", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnEnviar, btnCancelar);
        dialog.getDialogPane().setContent(content);

        // Feedback visual ao enviar
        dialog.setResultConverter(buttonType -> {
            if (buttonType == btnEnviar) {
                RadioButton estrelaSelecionada = (RadioButton) grupoEstrelas.getSelectedToggle();
                if (estrelaSelecionada != null) {
                    int avaliacao = estrelas.getChildren().indexOf(estrelaSelecionada) + 1;
                    String comentarioTexto = comentario.getText();
                    salvarAvaliacao(titulo, avaliacao, comentarioTexto);
                    mostrarNotificacao("Obrigado!", "Sua avaliação foi registrada com sucesso.");
                } else {
                    mostrarNotificacao("Atenção", "Por favor, selecione uma quantidade de estrelas.");
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
        HBox lista = new HBox(24);
        lista.setAlignment(Pos.CENTER_LEFT);
        lista.getChildren().addAll(
            criarLivroLinha("https://m.media-amazon.com/images/I/81QKQ9mwV7L._AC_UF1000,1000_QL80_.jpg", "O Nome do Vento", "Patrick Rothfuss", "Fantasia", 0, false, 4.6, 150),
            criarLivroLinha("https://m.media-amazon.com/images/I/81QKQ9mwV7L._AC_UF1000,1000_QL80_.jpg", "A Roda do Tempo", "Robert Jordan", "Fantasia", 0, false, 4.5, 120),
            criarLivroLinha("https://m.media-amazon.com/images/I/81AFgE3E6-L.jpg", "O Pequeno Príncipe", "Antoine de Saint-Exupéry", "Aventura", 0, false, 4.8, 200)
        );
        contentArea.getChildren().addAll(titulo, lista);
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
        Label lblValor = new Label(valor.replace("R$", "Kz"));
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
        Label lblValor = new Label(valor.replace("R$", "Kz"));
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

    // Método utilitário para mostrar notificações amigáveis
    private void mostrarNotificacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Modal animado para alterar PIN do usuário
    private void mostrarDialogoAlterarPIN() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Alterar PIN");
        dialog.setHeaderText("Altere seu PIN de acesso");

        VBox content = new VBox(14);
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.CENTER);

        Label lblAtual = new Label("PIN atual:");
        lblAtual.setFont(Font.font("Arial", 13));
        PasswordField campoAtual = new PasswordField();
        campoAtual.setPromptText("Digite seu PIN atual");
        campoAtual.setMaxWidth(180);

        Label lblNovo = new Label("Novo PIN:");
        lblNovo.setFont(Font.font("Arial", 13));
        PasswordField campoNovo = new PasswordField();
        campoNovo.setPromptText("Digite o novo PIN");
        campoNovo.setMaxWidth(180);

        Label lblConfirma = new Label("Confirmar novo PIN:");
        lblConfirma.setFont(Font.font("Arial", 13));
        PasswordField campoConfirma = new PasswordField();
        campoConfirma.setPromptText("Confirme o novo PIN");
        campoConfirma.setMaxWidth(180);

        content.getChildren().addAll(lblAtual, campoAtual, lblNovo, campoNovo, lblConfirma, campoConfirma);

        ButtonType btnSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(btnSalvar, btnCancelar);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == btnSalvar) {
                String atual = campoAtual.getText();
                String novo = campoNovo.getText();
                String confirma = campoConfirma.getText();
                // Simulação de validação (substitua pela lógica real)
                if (atual == null || atual.isEmpty()) {
                    mostrarNotificacao("Atenção", "Digite o PIN atual.");
                } else if (novo == null || novo.length() < 4) {
                    mostrarNotificacao("Atenção", "O novo PIN deve ter pelo menos 4 dígitos.");
                } else if (!novo.equals(confirma)) {
                    mostrarNotificacao("Atenção", "Os PINs não coincidem.");
                } else if (atual.equals(novo)) {
                    mostrarNotificacao("Atenção", "O novo PIN deve ser diferente do atual.");
                } else {
                    // Aqui você faria a validação real do PIN atual e salvaria o novo PIN
                    mostrarNotificacao("Sucesso!", "PIN alterado com sucesso.");
                }
            }
            return null;
        });

        dialog.showAndWait();
    }
} 