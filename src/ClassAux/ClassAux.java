package ClassAux;

import Book.Book;
import ClassMother.ListProgram;

import java.util.Random;

public class ClassAux {

    public static void clearScreen() {
        try {
            // Para sistemas Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            // Para sistemas Unix/Linux/Mac
            else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception _) {}
    }
    public static void loadFile() throws InterruptedException {
        for (int i = 0; i <=100 ; i+=10) {
            System.out.print("\n\nCarregando Arquivos em ... " + i + "%");
            Thread.sleep(300);
            clearScreen();
        }
    }
    public static void loadDelete() throws InterruptedException {
        System.out.print("\n\nRemovendo Arquivo ");

        for (int j = 0; j <3 ; j++) {
            for (int i = 0; i <3 ; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
            clearScreen();
        }
    }
    public static void save() throws InterruptedException {
        Random random=new Random();

        System.out.print("\n\nGuardando Dados em ");

        for (int i = 1; i <=3 ; i++) {
            System.out.print(".");
            Thread.sleep(1000);
        }
        clearScreen();
        for (int i = 1; i <=10 ; i++) {
            int r=random.nextInt(60);
            if(i==10){
                System.out.print("\nGuardando Dados em ... 0seg");
                System.out.println("\n     Dados Guardados");
                Thread.sleep(300);
            }else{
            System.out.print("\nGuardando Dados em ... "+r+"seg");
            }
            Thread.sleep(500);
            clearScreen();
        }
    }
    public static void refresh() throws InterruptedException {
        System.out.print("\n\nActualizando Dados ");

        for (int j = 0; j <3 ; j++) {
            for (int i = 0; i <3 ; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
            clearScreen();
        }
    }
    public static void bolonCont (int num, char charSymbol) throws InterruptedException {
        for (int i = 1; i <=num ; i++) {
            System.out.print(charSymbol);
            Thread.sleep(100);
        }
    }

    public static void search(String term){
        int index;
        if (ListProgram.listTitle.contains(term)){
            index=ListProgram.listTitle.indexOf(term);
        } else if (ListProgram.listAutor.contains(term)) {
            index=ListProgram.listAutor.indexOf(term);
        } else{
            index=Integer.parseInt(term);
        }
        if (index > Book.numBook || index < 0) {
            System.out.println("Livro não localizado! Verifique a sua entrada.");
        } else {
            if (index==Book.numBook){
                index--;
            }else {
                String title = ListProgram.listTitle.get(index);
                String autor=ListProgram.listAutor.get(index);
                String editionName=ListProgram.listEditionName.get(index);
                String langBook=ListProgram.listLangBook.get(index);
                String genBook=ListProgram.listGenBook.get(index);
                int yearPubli=ListProgram.listYearPubli.get(index);
                int numPag=ListProgram.listNumPag.get(index);
                String dateEmi=ListProgram.listDateEmi.get(index);

                System.out.println("\nResultado da pesquisa\n");

                System.out.println("Título: "+title);
                System.out.println("Autor: "+autor);
                System.out.println("Editora: "+editionName);
                System.out.println("Idioma: "+langBook);
                System.out.println("Gênero: "+genBook);
                System.out.println("Data de Publicação: "+yearPubli);
                System.out.println("Número de Páginas: "+numPag);
                System.out.println("Data de Emissão: "+dateEmi);
            }
        }
    }
}