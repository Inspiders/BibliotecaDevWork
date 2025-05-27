package com.biblioteca;

public class Livro {
    private String titulo;
    private String autor;
    private String genero;
    private int quantidade;

    public Livro(String titulo, String autor, String genero, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + genero + ") - Disponível: " + quantidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
} 