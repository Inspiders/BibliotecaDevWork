package com.biblioteca;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private boolean admin;

    public Usuario(String nome, String email, String senha, boolean admin) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public boolean isAdmin() { return admin; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setAdmin(boolean admin) { this.admin = admin; }
} 