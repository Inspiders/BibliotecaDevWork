import Book.Book;
import ClassAux.ClassAux;
import ClassMother.ListProgram;
import User.User;
import java.time.LocalDateTime;
import java.util.Scanner;


public class ADM {
    static Scanner read= new Scanner(System.in);
    static private final String pinC= "9725",userC= "admUser".toLowerCase();
    static LocalDateTime date = LocalDateTime.now();
    static int year = date.getYear();
    public static void adm() throws InterruptedException {
        int id = 1;
        while (id != 0) {
            System.out.println("\n====================   MODO ADMINISTRADOR   ====================\n");
            System.out.println("Bem-vindo ao modo Administrador!");
            System.out.println("1 - Adicionar Livro");
            System.out.println("2 - Listar Livro");
            System.out.println("3 - Eliminar Livro");
            System.out.println("4 - Listar Conta");
            System.out.println("5 - Eliminar Conta");
            System.out.println("6 - Ver Livro Reservado (indisponível)");
            System.out.println("7 - Ver Livro Emprestado (indisponível)");
            System.out.println("8 - Gerenciar Multas (indisponível)");
            System.out.println("9 - Resumo do Sistema");
            System.out.println("0 - SAIR");
            System.out.println("Digite 'sair' para voltar ao menu principal.");
            System.out.print("\nEscolha uma das opções acima: ");

            String input = read.next();
            if (input.equalsIgnoreCase("sair") || input.equals("0")) {
                id = 0;
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
                case 1:
                    Book.addBook();
                    break;
                case 2:
                    ClassAux.loadFile();
                    do {
                        Book.listinerBook();
                        System.out.println("\n1 - Pesquisar Livro");
                        System.out.println("2 - Atualizar Dados");
                        System.out.print("---> ");
                        User.dec = read.nextInt();
                        switch (User.dec) {
                            case 1:
                                System.out.print("\nPesquise o livro desejado: ");
                                String text = read.next();
                                ClassAux.search(text);
                                System.out.println("\n\nDeseja [0 - Sair] / [1 - Continuar]");
                                System.out.print("---> ");
                                id = read.nextInt();
                                break;
                            case 2:
                                System.out.print("\nDigite o ID do livro desejado: ");
                                id = read.nextInt();
                                refrech(id);
                                System.out.println("\n\nDeseja [0 - Sair] / [1 - Continuar]");
                                System.out.print("---> ");
                                id = read.nextInt();
                                break;
                            default:
                                System.err.println("\nOpção inválida!");
                                System.out.println("\n1 - M E N U");
                                System.out.println("0 - S A I R");
                                System.out.print("\nEscolha uma das opções acima: ");
                                id = read.nextInt();
                                break;
                        }
                        ClassAux.clearScreen();
                    } while (id != 0);
                    break;
                case 3:
                    Book.deleteBook();
                    break;
                case 4:
                    User.listinerCont();
                    System.out.println("\n1 - M E N U");
                    System.out.println("0 - S A I R");
                    System.out.print("\nEscolha uma das opções acima: ");
                    id = read.nextInt();
                    break;
                case 5:
                    User.deleteCont();
                    break;
                case 6:
                case 7:
                case 8:
                    System.out.println("\nOpção indisponível no momento.");
                    System.out.println("\n1 - M E N U");
                    System.out.println("0 - S A I R");
                    System.out.print("\nEscolha uma das opções acima: ");
                    id = read.nextInt();
                    break;
                case 9:
                    resumoSistema();
                    System.out.println("\n1 - M E N U");
                    System.out.println("0 - S A I R");
                    System.out.print("\nEscolha uma das opções acima: ");
                    id = read.nextInt();
                    break;
                default:
                    System.out.println("\nOpção inválida! Por favor, tente novamente.");
                    break;
            }
            ClassAux.clearScreen();
        }
        ClassAux.clearScreen();
        System.out.println("\nSaindo do modo administrador...\n");
    }

    // Função de resumo do sistema
    public static void resumoSistema() {
        System.out.println("\n==========   RESUMO DO SISTEMA   ==========");
        System.out.println("Total de usuários cadastrados: " + User.numUser);
        System.out.println("Total de livros cadastrados: " + Book.numBook);
    }

    public static void login() throws InterruptedException {
        String pin, user;
        int cont=1;

        while (cont<=3) {
            ClassAux.clearScreen();
            System.out.println("\n\n==========   LOGIN   ==========");

            System.out.print("Insira o seu usuário: ");
            user = read.next().toLowerCase();
            System.out.print("Insira o PIN de Verificação: ");
            pin = read.next();


            if (pinC.equals(pin) && userC.equals(user)) {
                ClassAux.clearScreen();
                System.out.println("\n==========   LOGIN COM SUCESSO   ==========");
                ClassAux.bolonCont(43,'.');

                adm();
                break;
            } else {

                System.out.println("\n\nCredências Inválidas!");

                System.out.println("\n" + cont + "ª de 3 Tentativas.\n");
                if (cont == 3) {
                    System.out.println("Escedeste o número maximo de tentativas!\n");
                    Thread.sleep(5000);
                    break;
                }
                Thread.sleep(2000);

                cont++;
            }
        }
    }

    static void refrech(int id) throws InterruptedException {
        int index=id-1;

        if (id > Book.numBook || id < 1) {
            System.out.println("Livro não localizado! Verifique a sua entrada.");
        } else {
            ClassAux.clearScreen();
            String title = ListProgram.listTitle.get(index);
            String autor=ListProgram.listAutor.get(index);
            String editionName=ListProgram.listEditionName.get(index);
            String genBook=ListProgram.listGenBook.get(index);
            int yearPubli=ListProgram.listYearPubli.get(index);
            int numPag=ListProgram.listNumPag.get(index);
            String dateEmi=ListProgram.listDateEmi.get(index);

            System.out.println("\nDados Actuais\n");

            System.out.println("Título: "+title);
            System.out.println("Autor: "+autor);
            System.out.println("Editora: "+editionName);
            System.out.println("Gênero: "+genBook);
            System.out.println("Data de Publicação: "+yearPubli);
            System.out.println("Número de Páginas: "+numPag);
            System.out.println("Data de Emissão: "+dateEmi);
            System.out.println("\n");

            System.out.println("\nAtualizar Dados Existente\n");

            System.out.print("Novo título: ");
            ListProgram.listTitle.set(index,read.next());

            System.out.print("Novo autor: ");
            ListProgram.listAutor.set(index,read.next());

            System.out.print("Nova editora: ");
            ListProgram.listEditionName.set(index,read.next());

            System.out.print("Novo gênero: ");
            ListProgram.listGenBook.set(index,read.next());

            System.out.print("Novo ano de publicação: ");
            ListProgram.listYearPubli.set(index,read.nextInt());

            System.out.print("Novo número de páginas: ");
            ListProgram.listNumPag.set(index,read.nextInt());

            System.out.println("Nova data de emissão: ");
            int dayEmi,mothEmi,yearEmi;
            do {
                System.out.print("---> Dia: ");
                dayEmi = read.nextInt();
                System.out.print("---> Mês: ");
                mothEmi = read.nextInt();
                System.out.print("---> Ano: ");
                yearEmi = read.nextInt();
            }while (dayEmi>31 || dayEmi<1 ||mothEmi>12 || mothEmi<1 || yearEmi<1 || yearEmi>year || yearEmi>yearPubli);
            dateEmi = (dayEmi + "/" + mothEmi + "/" + yearEmi);
            ListProgram.listDateEmi.set(index,dateEmi);

            ClassAux.refresh();
            System.out.println("✅ Dados atualizados com sucesso!");
            Thread.sleep(2000);
        }
    }
}