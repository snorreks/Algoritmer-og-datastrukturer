package oving5.del1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Hashtabell hashtabell = new Hashtabell(137);

        /*              Les fra fil         */
        String studenter = "src/oving5/del1/studenter.txt";
        FileReader file = new FileReader(studenter);
        BufferedReader buffer = new BufferedReader(file);
        String transaksjon = buffer.readLine();
        while (transaksjon != null) {
            if (!hashtabell.setInn(transaksjon)) System.out.println("Ouff");
            transaksjon = buffer.readLine();
        }
        buffer.close();
        /*              ------------         */

        hashtabell.displayHash();
        System.out.println((hashtabell.checkNavn("Sørlie,Lars") ? "Lars er i tabellen" : "Lasr er ikke i tabellen"));
        System.out.println("Lastfaktoren:                                   " + hashtabell.getLastFaktoren());
        System.out.println("Antall kollisjoner:                             " + hashtabell.antallKollisjoner);
        System.out.println("Gjennomsnittlig antall kollisjoner per person:  " + hashtabell.getAvgKolliPrPerson());
    }
}
