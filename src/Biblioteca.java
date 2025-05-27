import ClassAux.*;
import ClassMother.ListProgram;
import User.User;
import java.util.Scanner;

public class Biblioteca {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        menuPrincipal();
    }

    // Novo método para o menu principal
    public static void menuPrincipal() throws InterruptedException {
        while (true) {
            System.out.println("\n==========   BEM-VINDO À BIBLIOTECA   ==========");
            System.out.println("1 - Entrar");
            System.out.println("2 - Criar Conta");
            System.out.println("15115 - Modo Administrador");
            System.out.println("0 - Fechar App");
            System.out.println("Digite 'sair' a qualquer momento para encerrar.");
            System.out.print("\nEscolha uma das opções acima: ");

            String input = read.next();
            if (input.equalsIgnoreCase("sair") || input.equals("0")) {
                break;
            }
            int option = -1;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nOpção inválida! Por favor, digite um número válido.");
                ClassAux.clearScreen();
                continue;
            }
            ClassAux.clearScreen();
            switch (option) {
                case 15115:
                    menuADM();
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    User.addUser();
                    break;
                default:
                    System.out.println("\nOpção inválida! Por favor, tente novamente.");
                    break;
            }
            ClassAux.clearScreen();
        }
        ClassAux.clearScreen();
        System.out.println("\n\nPROGRAMA ENCERRADO\n");
    }

    // Novo método para login de administrador
    public static void menuADM() throws InterruptedException {
        System.out.println("\n==========   MODO ADMINISTRADOR   ==========");
        System.out.print("Insira a palavra-passe:  ");
        String passWord = read.next();
        if (passWord.equals("segredo93*#95")) {
            ADM.login();
        } else {
            System.out.println("Senha Incorreta!");
            Thread.sleep(1000);
        }
    }

    // Método de login de usuário
    public static void login() throws InterruptedException {
        String pin, user;
        System.out.println("\n\n==========   LOGIN   ==========");
        System.out.print("Insira o seu usuário: ");
        user = read.next().toUpperCase();
        System.out.print("Insira o seu PIN: ");
        pin = read.next();
        ClassAux.clearScreen();
        if (User.numUser > 0) {
            int index = ListProgram.listUser.indexOf(user);
            if (index != -1 && ListProgram.listPin.get(index).equals(pin)) {
                String gener = ListProgram.listGenUser.get(index);
                String name = ListProgram.listName.get(index);
                System.out.println("\n==========   LOGIN COM SUCESSO   ==========");
                User.UserCont(gener, name, user, index);
            } else {
                System.out.println("\n\nConta Inexistente ou PIN incorreto.");
                Thread.sleep(1000);
            }
        } else {
            System.out.println("\nConta Inexistente!");
            Thread.sleep(1000);
        }
    }
}