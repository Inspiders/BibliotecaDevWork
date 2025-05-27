package com.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static List<User> users = new ArrayList<>();
    private String username;
    private String pin;
    private boolean isAdmin;

    static {
        // Adicionar usuário admin padrão
        users.add(new User("admin", "1234", true));
    }

    public User(String username, String pin, boolean isAdmin) {
        this.username = username;
        this.pin = pin;
        this.isAdmin = isAdmin;
    }

    public static int autenticar(String username, String pin) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.username.equals(username) && user.pin.equals(pin)) {
                return i;
            }
        }
        return -1;
    }

    public static void adicionarUsuario(String username, String pin) {
        users.add(new User(username, pin, false));
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
} 