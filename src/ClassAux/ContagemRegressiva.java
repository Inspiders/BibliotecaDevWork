package ClassAux;

import java.time.LocalTime;
import java.time.Duration;
import java.util.Scanner;

public class ContagemRegressiva {
    static LocalTime time = LocalTime.now();
    static String texto;
    int hour = time.getHour();
    static int min = time.getMinute();
    static int seg = time.getSecond();

    public static void main(String[] args) throws InterruptedException {
        cont();
        System.out.println(texto);
    }

    //public void controlerTimeEmprest(int day,int hour,int min,int seg) throws InterruptedException {
    public static String cont() throws InterruptedException {


        int id=0;
        String clock = "";
        Scanner input= new Scanner(System.in);
        // Hora inicial e hora final (Os objectos serão usados para indicar o dia de empre. de um livro e o dia de devolução)
        LocalTime inicio = LocalTime.of(0, 1, 0);
        LocalTime fim = LocalTime.of(0, 0, 0);


        long horas = 0;
        long minutos= 0;
        long segundo=0;

        // Cálculo da duração
        Duration duracao = Duration.between(fim, inicio);

        // Loop de contagem regressiva
        do {
            System.out.println("Data passada: "+texto+"\n\n");
            if (!duracao.isNegative() && !duracao.isZero()) {
                long segundos = duracao.getSeconds();

                 horas = segundos / 3600;
                 minutos = (segundos % 3600) / 60;
                 segundo = segundos % 60;

                System.out.printf("%02d:%02d:%02d\n", horas, minutos, segundo);
                clock = (horas + " : " + minutos + " : " + " : " + segundo);

                //Thread.sleep(1000); // Espera 1 segundo
                duracao = duracao.minusSeconds(1);
            }else {
                clock=("O prazo de emprestimo chegou ao fim!");
            }

            System.out.println("Desejas continuar?\n[1 -SIM]\n[0 - NÃO]");
            id=input.nextInt();
        }while (id==1);
        texto=clock;
        return  clock;
    }

}
