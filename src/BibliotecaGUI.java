import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BibliotecaGUI extends Application {
    
    // Paleta de cores
    private static final String AZUL_ESCURO = "#2C3E50";
    private static final String AZUL_CLARO = "#3498DB";
    private static final String CINZA_CLARO = "#ECF0F1";
    private static final String BRANCO = "#FFFFFF";
    private static final String VERMELHO_SUAVE = "#E74C3C";
    private static final String VERDE_SUAVE = "#2ECC71";
    
    private Label statusLabel;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Biblioteca");
        
        // Layout principal
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + CINZA_CLARO + ";");
        
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
        VBox header = new VBox();
        header.setStyle("-fx-background-color: " + AZUL_ESCURO + "; -fx-padding: 15;");
        header.setAlignment(Pos.CENTER);
        
        Label titulo = new Label("📚 Sistema de Biblioteca");
        titulo.setStyle("-fx-text-fill: " + BRANCO + "; -fx-font-size: 24px; -fx-font-weight: bold;");
        
        Label subtitulo = new Label("Gerencie sua biblioteca de forma prática e eficiente");
        subtitulo.setStyle("-fx-text-fill: " + CINZA_CLARO + "; -fx-font-size: 14px;");
        
        header.getChildren().addAll(titulo, subtitulo);
        return header;
    }
    
    private VBox criarAreaCentral() {
        VBox area = new VBox(20);
        area.setPadding(new Insets(30));
        area.setAlignment(Pos.CENTER);
        area.setStyle("-fx-background-color: " + BRANCO + ";");
        
        // Container para os botões
        VBox botoesContainer = new VBox(15);
        botoesContainer.setAlignment(Pos.CENTER);
        botoesContainer.setMaxWidth(300);
        
        // Botões do menu principal
        Button btnEntrar = criarBotao("🔑 Entrar", AZUL_CLARO);
        btnEntrar.setOnAction(e -> mostrarTelaLogin());
        
        Button btnCriarConta = criarBotao("➕ Criar Conta", VERDE_SUAVE);
        btnCriarConta.setOnAction(e -> mostrarTelaCriarConta());
        
        Button btnAdmin = criarBotao("👨‍💼 Modo Administrador", AZUL_ESCURO);
        btnAdmin.setOnAction(e -> mostrarTelaAdmin());
        
        // Adicionar efeito de sombra ao container
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.web(AZUL_ESCURO, 0.3));
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
        rodape.setStyle("-fx-background-color: " + AZUL_ESCURO + ";");
        
        statusLabel = new Label("✅ Sistema pronto para uso");
        statusLabel.setStyle("-fx-text-fill: " + BRANCO + "; -fx-font-size: 12px;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label creditos = new Label("Biblioteca v1.0");
        creditos.setStyle("-fx-text-fill: " + CINZA_CLARO + "; -fx-font-size: 12px;");
        
        rodape.getChildren().addAll(statusLabel, spacer, creditos);
        return rodape;
    }
    
    private Button criarBotao(String texto, String cor) {
        Button btn = new Button(texto);
        btn.setPrefWidth(250);
        btn.setPrefHeight(40);
        btn.setStyle(
            "-fx-background-color: " + cor + ";" +
            "-fx-text-fill: " + BRANCO + ";" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;" +
            "-fx-cursor: hand;"
        );
        
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
        // TODO: Implementar tela de login
        statusLabel.setText("🔑 Tela de login em desenvolvimento");
    }
    
    private void mostrarTelaCriarConta() {
        // TODO: Implementar tela de criar conta
        statusLabel.setText("➕ Tela de criar conta em desenvolvimento");
    }
    
    private void mostrarTelaAdmin() {
        // TODO: Implementar tela de administrador
        statusLabel.setText("👨‍💼 Tela de administrador em desenvolvimento");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
} 