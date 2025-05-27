package com.biblioteca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Recursos {
    // URLs das imagens
    private static final String LOGO_URL = "https://cdn-icons-png.flaticon.com/512/2232/2232688.png";
    private static final String BACKGROUND_URL = "https://img.freepik.com/free-photo/books-arrangement-with-copy-space_23-2148866885.jpg";
    private static final String USER_ICON_URL = "https://cdn-icons-png.flaticon.com/512/1077/1077114.png";
    private static final String BOOK_ICON_URL = "https://cdn-icons-png.flaticon.com/512/2232/2232688.png";
    private static final String ADMIN_ICON_URL = "https://cdn-icons-png.flaticon.com/512/1077/1077063.png";
    
    // Cores do tema
    public static final String AZUL_ESCURO = "#2C3E50";
    public static final String AZUL_CLARO = "#3498DB";
    public static final String CINZA_CLARO = "#ECF0F1";
    public static final String BRANCO = "#FFFFFF";
    public static final String VERMELHO_SUAVE = "#E74C3C";
    public static final String VERDE_SUAVE = "#2ECC71";
    
    // Estilos CSS
    public static final String ESTILO_BOTAO = 
        "-fx-background-color: %s;" +
        "-fx-text-fill: " + BRANCO + ";" +
        "-fx-font-weight: bold;" +
        "-fx-font-size: 14px;" +
        "-fx-border-radius: 5;" +
        "-fx-background-radius: 5;" +
        "-fx-cursor: hand;" +
        "-fx-padding: 10 20;";
    
    public static final String ESTILO_CAMPO_TEXTO = 
        "-fx-background-color: " + CINZA_CLARO + ";" +
        "-fx-padding: 10;" +
        "-fx-font-size: 14px;" +
        "-fx-border-radius: 5;" +
        "-fx-background-radius: 5;";
    
    public static final String ESTILO_CABECALHO = 
        "-fx-background-color: " + AZUL_ESCURO + ";" +
        "-fx-padding: 15;";
    
    public static final String ESTILO_RODAPE = 
        "-fx-background-color: " + AZUL_ESCURO + ";" +
        "-fx-padding: 10 20;";
    
    // Métodos para criar ImageViews
    public static ImageView criarLogo(double tamanho) {
        ImageView logo = new ImageView(new Image(LOGO_URL));
        logo.setFitWidth(tamanho);
        logo.setFitHeight(tamanho);
        return logo;
    }
    
    public static ImageView criarBackground(double largura, double altura) {
        ImageView background = new ImageView(new Image(BACKGROUND_URL));
        background.setFitWidth(largura);
        background.setFitHeight(altura);
        return background;
    }
    
    public static ImageView criarIconeUsuario(double tamanho) {
        ImageView icone = new ImageView(new Image(USER_ICON_URL));
        icone.setFitWidth(tamanho);
        icone.setFitHeight(tamanho);
        return icone;
    }
    
    public static ImageView criarIconeLivro(double tamanho) {
        ImageView icone = new ImageView(new Image(BOOK_ICON_URL));
        icone.setFitWidth(tamanho);
        icone.setFitHeight(tamanho);
        return icone;
    }
    
    public static ImageView criarIconeAdmin(double tamanho) {
        ImageView icone = new ImageView(new Image(ADMIN_ICON_URL));
        icone.setFitWidth(tamanho);
        icone.setFitHeight(tamanho);
        return icone;
    }
} 