package User;

import Bank.ContBank;
import Book.Book;
import ClassAux.*;
import ClassMother.ListProgram;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class User {

    static Scanner read = new Scanner(System.in);
    static Random random = new Random();

    public static int dec;

    public static int numUser=0; //Números de user cadastrado na bibliteca
    static int numBookEmpre = 0, numBookReser = 0;//Números de livros emprestados e reservados

    static String pin = "", user, genUser, nameComplet, dateCad;

    public static void addUser() throws InterruptedException {
        LocalDateTime date = LocalDateTime.now();

        int hour = date.getHour(), min = date.getMinute(), seg = date.getSecond();
        int year = date.getYear(), moth = date.getMonthValue(), day = date.getDayOfMonth();

        int n1 = 0, n2 = 0, n3, n4;

        String name, name1;

        System.out.println("\n\n\n==========   CADASTRA-SE AQUI   ==========\n");


        Construcao_do_PIN:{
            for (int i = 0; i < 100; i++) {
                n1 = random.nextInt(0, 10);
                n2 = random.nextInt(0, 10);
                n3 = random.nextInt(0, 10);
                n4 = random.nextInt(0, 10);

                pin = ("%d%d%d%d".formatted(n1, n2, n3, n4));
            }
        }


        System.out.print("Infome por favor o seu nome completo: ");
        name1 = read.next();
        name = read.nextLine();
        /*do {
            System.out.println("\n\nO saldo inicial deve ser no mínimo de 5.000.00Kz");
            System.out.print("Informe o saldo inicial: ");
            saldIn = read.nextDouble();
        }while (saldIn<5000);*/ //Money, será adicionado

        System.out.println("\nInforme o seu gênero:\nInsira 1 para <<Masculino>> e 2 para <<Femenino>>");
        System.out.print("----> ");
        int genero = read.nextInt();

        while (genero != 1 && genero != 2) {

            System.err.println("\n\nSexo Inválido!");

            System.out.println("\nDigite 1 para <<Masculino>> e 2 para <<Femenino>>");
            System.out.print("----> ");
            genero= read.nextInt();
        }

        if (genero == 1) {
            genUser = ("Masculino").toLowerCase();
        } else if (genero == 2) {
            genUser = ("Femenino").toLowerCase();
        }

        System.out.println("\n\nAgora, precisas de definir um usuário!");

        System.out.println("Sujestão de usuário [" + name1 + (n1 + "" + n2) + "]");
        System.out.print("Informe um usuário: ");
        user = read.next().toUpperCase();

        nameComplet = (name1 + name).toUpperCase();
        dateCad = (day + "/" + moth + "/" + year + "   " + hour + "h:" + min + "min:" + seg+"seg");

        ClassAux.save();

        System.out.println("\n\n\nUSUÁRIO CADASTRADO COM SUCESSO!\n    INFORMAÇÕES DA CONTA\n");

        System.out.println("Titular: " + nameComplet);
        System.out.println("Gênero: " + genUser);
        System.out.println("Usuário: " + user);
        System.out.println("PIN: " + pin);
        System.out.println("Data de Cadastro: " + dateCad);

        numUser++;
        int index = (numUser - 1);

        ListProgram.listName.add(index, nameComplet);
        ListProgram.listPin.add(index, pin);
        ListProgram.listUser.add(index, user);
        ListProgram.listGenUser.add(index, genUser);
        ListProgram.listDateCad.add(index, dateCad);

        System.out.println("\n\n1 - OK");
        dec = read.nextInt();

        System.out.println("\n\n\n");
    }//Don

    public static void deleteCont() throws InterruptedException {
        int id,index;
        listinerCont();

        if (numUser>0){
            System.out.println("\nPara eliminar uma conta do sistema precisas de digitar o ID da conta desejada.");
            Thread.sleep(1500);
            System.out.print("Digite o ID da conta que pretendes remover do sistema: ");
            id=read.nextInt();
            index=id-1;

            ClassAux.loadDelete();
            if (id>numUser || id<1){
                System.out.println("ID não localizado! Verifique a sua entrada\nNenhum livro foi removido da biblioteca!");
            }else {
                //Aqui vamos uzar os métodos list para remover o livro desajado [listName.remove(index)]

                numUser--;

                ListProgram.listName.remove(index);
                ListProgram.listPin.remove(index);
                ListProgram.listUser.remove(index);
                ListProgram.listGenUser.remove(index);
                ListProgram.listDateCad.remove(index);

                System.out.println("\n\nConta Removida Com Sucesso!");
                System.out.println("\n\n1 - OK");
                dec = read.nextInt();
            }
        }
    } //Don (ADM)

    public static void listinerCont() {
        //Inicialaizer.inicialaizerUser(); //Inicializador
        if (numUser > 0) {
            System.out.printf("\n\n|%20s|\n", "-----------------------------------------------------------------------------------------------------------");
            System.out.printf("|%60s%48s\n", "LISTA DE CONTA", "|");
            System.out.printf("|%20s|\n", "-----------------------------------------------------------------------------------------------------------");
            System.out.printf("|%4s%20s %3s%3s %4s%3s%25s%7s\n", "ID", " |                TITULAR            ", "|   GÊNERO    ", "|  USUÁRIO ", "| PIN", "|"," DATA DE CADASTRO ","|");
            System.out.printf("|%4s%20s%5s%10s%4s%4S|\n", "-----", "|------------------------------------", "|-------------", "|-----------", "|------", "|-------------------------------");

            for (int i = 0; i < numUser; i++) {
                System.out.printf("|%3d %s %33s %2s %10s %2s %6s%5s %4s%2s  %10s%3s\n",(i+1)," |",ListProgram.listName.get(i)," |",ListProgram.listGenUser.get(i),"|",ListProgram.listUser.get(i),"|",ListProgram.listPin.get(i),"|",ListProgram.listDateCad.get(i),"|");
            }

            System.out.printf("|%4s%20s%5s%10s%4s%4s|\n", "-----", "|------------------------------------", "|-------------", "|-----------", "|------", "|-------------------------------");
            System.out.printf("|%16s%8d %83s\n", "Total De Conta:",numUser,"|");
            System.out.printf("|%20s|\n","-----------------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("\n\nNENHUMA CONTA EXISTENTE!\n");
        }
    } //Don (ADM)

    public static void UserCont(String genero,String name, String user,int index) throws InterruptedException {

        int id, option;
        String text;

        if (genero.equals("masculino")) {
            genero = "o";
        } else if (genero.equals("femenino")) {
            genero = "a";
        }
        System.out.println("     Seja bem-vind" + genero + " à biblioteca XXX!");
        do {
            System.out.println("\n\n1 - Informação da Conta");
            System.out.println("2 - Livros Disponiveis");
            System.out.println("3 - Galéria");
            System.out.println("4 - Actulizar Saldo");
            System.out.println("5 - Ajuda");
            System.out.println("0 - SAIR");
            System.out.print("\nEscolha uma das opções acima: ");
            option = read.nextInt();

            ClassAux.clearScreen();
            switch (option) {
                case 1:
                    inforCont(name, user,index);
                    id = 1;
                    break;

                case 2:
                    ClassAux.loadFile();
                    do {
                        Book.listinerBook();

                        System.out.println("\n1 - Pesquisar Livro");
                        System.out.println("2 - Emprestar Livro");
                        System.out.println("3 - Reservar Livro");
                        //System.out.println("4 - Comprar Livro");
                        System.out.print("--->");
                        User.dec = read.nextInt();
                        switch (User.dec) {
                            case 1:

                                System.out.print("\nPesquise o livro desejado: ");
                                text = read.next();
                                ClassAux.search(text);
                                System.out.println("\n\nDesejas [0 - Sair] / [1 - Continuar]");
                                System.out.print("---> ");
                                id = read.nextInt();
                                break;

                            case 2:
                                System.out.println("Opção indisponivel");
                                //Book.emprestBook();
                                System.out.println("\n\nDesejas [0 - Sair] / [1 - Continuar]");
                                System.out.print("---> ");
                                id = read.nextInt();
                                break;

                            case 3:
                                System.out.println("Opção indisponivel");
                                //Book.reserverBook();
                                System.out.println("\n\nDesejas [0 - Sair] / [1 - Continuar]");
                                System.out.print("---> ");
                                id = read.nextInt();
                                break;

                            case 4:
                                System.out.println("Opção indisponivel");
                                //byBook();
                                System.out.println("\n\nDesejas [0 - Sair] / [1 - Continuar]");
                                System.out.print("---> ");
                                id = read.nextInt();
                                break;

                            default:
                                System.err.println("\nError, Opção Inválida!");
                                System.out.println("\n1 - M E N U");
                                System.out.println("0 - S A I R");
                                System.out.print("\nEscolha uma das opções acima: ");
                                id = read.nextInt();
                                break;
                        }

                        ClassAux.clearScreen();
                    }while (id!=0);
                    id=1;
                    break;

                case 3:
                    galery(genero);
                    id = 1;
                    break;

                case 4:
                    ContBank.dep_Value(index);
                    id=1;
                    break;

                case 5:
                    helpUser("notepad C:/Users/TYLER/Documents/program/BibliotecaDevWork/src/User helpUser.txt");
                    id=1;
                    break;

                case 0:
                    id = 0;
                    break;

                default:
                    System.err.println("\nError, Opção Inválida!");
                    System.out.println("\n1 - M E N U");
                    System.out.println("0 - S A I R");
                    System.out.print("\nEscolha uma das opções acima: ");
                    id = read.nextInt();
                    break;
            }
            ClassAux.clearScreen();
        } while (id != 0);
    } //Quando estiver logado

    public static void inforCont(String titul, String user,int index) {

        do {
            System.out.println("\n=========== INFORMAÇÕES DA CONTA ===========\n");

            System.out.println("Dados Básicos: ");

            System.out.println("--> Titular da conta: " + titul);
            System.out.println("--> Usuário: " + user);
            //System.out.printf("%s%.2fKz\n","--> Saldo Actual: ", ContBank.saldActu(index));


            System.out.println("\n\nActividades (Acesse à <<GALÉRIA>> para ter mais informaçõoes)");

            System.out.println("--> Livros Emprestados (" + numBookEmpre + "):\n");
            System.out.println("\t");//Terá is livros emprestados

            System.out.println("--> Livros Reservados (" + numBookReser + "):\n");
            System.out.println("\t");//Terá is livros reservados

            System.out.println("\nDigite 0 para Sair");
            System.out.print("---> ");
            dec = read.nextInt();

            ClassAux.clearScreen();
        } while (dec != 0);

    }

    public static void galery(String genero) {

        System.out.println("\nSeja bem-vind" + genero + " à galéria!");
        System.out.println("\n1 - Livros Reservados");
        System.out.println("2 - Livros Emprestados");
        System.out.print("\nEscolha umas das opções à cima: ");
        int id = read.nextInt();

        switch (id){
            case 1:
                System.out.println("Nehum livro reservado");
                break;
            case 2:
                System.out.println("Nenhum livro emprestad");
                break;
        }

    }

    public static void helpUser(String locale){
         try {
                // Para sistemas Windows
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", locale).inheritIO().start().waitFor();
                }else{
                    System.out.println("Indisponivel");
                }
         } catch (Exception _) {}
    }

    /**
     * Autentica um usuário pelo nome de usuário e PIN.
     * @param user Nome de usuário
     * @param pin PIN do usuário
     * @return índice do usuário se autenticado, -1 caso contrário
     */
    public static int autenticar(String user, String pin) {
        for (int i = 0; i < numUser; i++) {
            if (ListProgram.listUser.get(i).equalsIgnoreCase(user) && ListProgram.listPin.get(i).equals(pin)) {
                return i;
            }
        }
        return -1;
    }
}