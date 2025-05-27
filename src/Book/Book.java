package Book;

import ClassMother.ListProgram;
import ClassAux.*;
import User.User;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Book{

    static Scanner read = new Scanner(System.in);
    public static int numBook,quantBook=0; //Números de livro na bibliteca
    public static String source;

    public static void addBook()throws InterruptedException {

        LocalDateTime date = LocalDateTime.now();

        int hour = date.getHour(), min = date.getMinute(), seg = date.getSecond();
        int year = date.getYear(), moth = date.getMonthValue(), day = date.getDayOfMonth();

        int dayEmi, mothEmi, yearEmi;
        int numPag;

        String autor, title, editionName;
        int yearPubli;
        String genBook, dateEmi, dateAdd;

        Cadastrar_Livro_na_biblioteca:{
            System.out.println("\n\n\n==========   CADASTRAR LIVRO   ==========\n");

            System.out.print("Informe por favor o nome do livro: ");
            title = read.nextLine();

            System.out.print("Informe o nome do autor: ");
            autor = read.nextLine();

            System.out.print("Insira o nome da editora: ");
            editionName = read.nextLine();

            do {
                System.out.print("\nInforme o ano de publicação: ");
                yearPubli = read.nextInt();
            }while (yearPubli<1 || yearPubli>year);

            do {
            System.out.print("\nInsira o número de páginas: ");
            numPag = read.nextInt();
            }while (numPag<1);

            System.out.print("Informe o gênero do livro: ");
            genBook = read.next();

            System.out.print("\nAgora farás a entrada da data de emissão do livro\n");
            Thread.sleep(1000);
            do {
                System.out.print("---> Dia: ");
                dayEmi = read.nextInt();
                System.out.print("---> Mês: ");
                mothEmi = read.nextInt();
                System.out.print("---> Ano: ");
                yearEmi = read.nextInt();
            }while (dayEmi>31 || dayEmi<1 ||mothEmi>12 || mothEmi<1 || yearEmi<1 || yearEmi>year || yearEmi>yearPubli);

            dateEmi = (dayEmi + "/" + mothEmi + "/" + yearEmi);
            dateAdd = (day + "/" + moth + "/" + year + "   " + hour + "h:" + min + "min:" + seg+"seg");
        }

        ClassAux.save();

        System.out.println("\n\n\nLIVRO ADICIONADO COM SUCESSO!\n   INFORMAÇÕES DO LIVRO\n");

        System.out.println("Título: "+title);
        System.out.println("Autor: "+autor);
        System.out.println("Editora: "+editionName);
        System.out.println("Gênero: "+genBook);
        System.out.println("Data de Publicação: "+yearPubli);
        System.out.println("Número de Páginas: "+numPag);
        System.out.println("Data de Emissão: "+dateEmi);
        System.out.println("Data de Cadastro: "+dateAdd);

        if( ListProgram.listTitle.contains(title) && ListProgram.listAutor.contains(autor))
        {
            int ind= ListProgram.listTitle.indexOf(title);
            int quant=ListProgram.listQuantBook.get(ind);

            quant++;
            ListProgram.listQuantBook.set(ind,quant);
        }else {
            numBook++;
            quantBook++;
            int index=(numBook-1);

            ListProgram.listTitle.add(index,title.toUpperCase());
            ListProgram.listAutor.add(index,autor.toUpperCase());
            ListProgram.listEditionName.add(index,editionName);
            ListProgram.listGenBook.add(index,genBook);
            ListProgram.listYearPubli.add(index,yearPubli);
            ListProgram.listNumPag.add(index,numPag);
            ListProgram.listDateEmi.add(index,dateEmi);
            ListProgram.listDateAdd.add(index,dateAdd);
            ListProgram.listQuantBook.add(index,quantBook);
        }

        System.out.println("\n\n1 - OK");
        User.dec = read.nextInt();
    } //Don (ADM)

    public static void deleteBook() throws InterruptedException {
        int id,index;
        listinerBook();
        if (numBook>0){
            System.out.println("\n\nPara eliminar um livro do sistema precisas de digitar o ID do livro desejado.");
            Thread.sleep(1500);
            System.out.print("Digite o ID do livro que pretendes remover da bibliotrca: ");
            id=read.nextInt();
            index=id-1;

            ClassAux.loadDelete();
            if (id>Book.numBook || id<1){
                System.out.println("ID não localizado! Verifique a sua entrada\nNenhum livro foi removido da biblioteca!");
            }else {
                //Aqui vamos uzar os métodos list para remover o livro desajado [listName.remove(index)]

                numBook--;
                ListProgram.listTitle.remove(index);
                ListProgram.listAutor.remove(index);
                ListProgram.listEditionName.remove(index);
                ListProgram.listLangBook.remove(index);
                ListProgram.listGenBook.remove(index);
                ListProgram.listYearPubli.remove(index);
                ListProgram.listNumPag.remove(index);
                ListProgram.listDateEmi.remove(index);
                ListProgram.listDateAdd.remove(index);

                System.out.println("\n\nLivro Removida Com Sucesso!");
                System.out.println("\n\n1 - OK");
                User.dec = read.nextInt();

            }
        }
    } //Don (ADM) não esta terminado

    public static void listinerBook() throws InterruptedException {
        inicialaizerBook(); //Inicializador
        if(numBook>0) {
            System.out.printf("\n\n|%50s|\n", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("|%90s%85s\n", "LISTA DE LIVRO", "|");
            System.out.printf("|%50s|\n", "------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("|%4s %4s%10s %20s %10s%3s%4s%8s %15s%3s\n", "ID", "| QUANTIDADE", " |                 AUTOR            ", "    |          TÍTULO        ", "    |      GÉNERO     ", "|  Nº PAG  ", "| ANO DE LANÇAMENTO", " | DATA DE EMISSÃO |", "EDITORA   ", "|");
            System.out.printf("|%4s%4s%10s%5s%10s%4s%4s%8s%10S|\n", "-----", "|------------", "|---------------------------------------", "|-----------------------------", "|-----------------", "|----------", "|-------------------", "|-----------------|", "------------------");

            for (int i = 0; i < numBook; i++) {
                System.out.printf("|%3d %2s %5d %6s %35s %3s %25s %3s %10s %2s %5d %4s  %10d%8s %12s%5s %14s%4s\n", (i + 1), "|", ListProgram.listQuantBook.get(i), "|", ListProgram.listAutor.get(i), " |", ListProgram.listTitle.get(i), "|", ListProgram.listGenBook.get(i), "     |", ListProgram.listNumPag.get(i), "|", ListProgram.listYearPubli.get(i), "|", ListProgram.listDateEmi.get(i), " |", ListProgram.listEditionName.get(i), " |");
            }
            System.out.printf("|%4s%4s%10s%5s%10s%4s%s%8s%10S|\n", "-----", "|------------", "|---------------------------------------", "|-----------------------------", "|-----------------", "|----------", "|-------------------", "|-----------------|", "------------------");
        }else {
            System.out.println("\n\nNENHUMA LIVRO DISPONIVEL!\n");
            Thread.sleep(1000);
        }
    }

    public static void inicialaizerBook() {

        if (Book.numBook<10){

            String []title={"bor","","","","fr","","ed","","","2w"};
            String []autor={"","gor","ff","","","de","","","s",""};
            String []edition={"","","","","","","","","",""};
            String []langBook={"","","","","","","","","",""};
            String []generBook={"","","","","","","","","",""};
            int []yerPublic={1976,1974,2000,1765,1895,1875,1900,1900,2025,2015};
            int []numPag={20,50,10,2000,40,45,100,150,54,60};
            String []dateEmi={"05/04/1973","29/03/1970","15/04/1986","10/06/1586","07/08/1886","20/09/1870","31/12/1890","03/03/1886","29/03/2007","17/11/2000"};

            for (int i = 0; i < 10; i++) {
                ListProgram.listTitle.add(i,title[i]);
                ListProgram.listAutor.add(i,autor[i]);
                ListProgram.listEditionName.add(i,edition[i]);
                ListProgram.listLangBook.add(i,langBook[i]);
                ListProgram.listGenBook.add(i,generBook[i]);
                ListProgram.listYearPubli.add(i,yerPublic[i]);
                ListProgram.listNumPag.add(i,numPag[i]);
                ListProgram.listDateEmi.add(i,dateEmi[i]);
                Book.numBook++;
                ListProgram.listQuantBook.add(i,1);
            }
        }
    } /*OBS*/

    public static void emprestBook() {

        System.out.println("\nDigite o ID do livro desejado: ");
        int id = read.nextInt();
        int index=id-1;
        if (id > numBook || id < 1) {
            System.out.println("Livro não localizado! Verifique a sua entrada.");
        } else {

        }
    }//Indisponivel

    public static void reserverBook() {
        System.out.println("\nDigite o ID do livro desejado: ");
        int id = read.nextInt();
        int index=id-1;
        if (id > numBook || id < 1) {
            System.out.println("Livro não localizado! Verifique a sua entrada.");
        } else {

        }
    }//Indisponivel

    public static void devolBook() {
        System.out.println("Opção Não Disponivel");
    } //Indisponivel
}