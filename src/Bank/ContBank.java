package Bank;

import java.util.ArrayList;
import java.util.Scanner;

public class ContBank{
    static Scanner read=new Scanner(System.in);

    public static ArrayList<Double> sald=new ArrayList<>();

    public static Double saldActu(int index){
        return sald.get(index);
    }
    public static void dep_Value(int index) {
        double value;
        double totalValue;
        System.out.println("\nDigite a quantidade de valor a depositar: ");
        value=read.nextDouble();
        if (value<=0){
            System.out.println("Nenhum valor foi depositado a sua conta.\nVerifique as Entradas!");
        }else{
            System.out.printf("\n%s %.2fKz\n","Saldo Encotrado:",sald.get(index));
            System.out.printf("%s %.2fKz\n","Valor Depositado:",value);
            totalValue =value+sald.get(index);
            System.out.printf("\n%s %.2fKz","Saldo Total:", totalValue);
            sald.set(index,totalValue);
        }
    }
    public static void sac_Value(int index,double value) {
        double totalValue;

        if (value<=0 || value>sald.get(index)){
            System.out.println("A compra não foi efectuada.\n Valor Insuficiente!");
        }else{
            System.out.printf("\n%s %.2fKz\n","Saldo Encotrado:",sald.get(index));
            System.out.printf("%s %.2fKz\n","Valor Retirado:",value);
            totalValue =sald.get(index)-value;
            System.out.printf("%s %.2fKz","Saldo Restante:", totalValue);
            sald.set(index,totalValue);
        }
    }//Comprar Livro
}