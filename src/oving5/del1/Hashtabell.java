package oving5.del1;

public class Hashtabell {

    String[] tabell;
    int arraySize;
    int antallKollisjoner = 0;
    int antallPersoner = 0;


    public Hashtabell(int size) {
        arraySize = size;
        tabell = new String[size];
    }

    public int getIndexVerdi(String navn) {
        int sum = 0;
        for (int i = 0; i < navn.length(); i++) {
            sum = (navn.charAt(i) * (7 * i + 1)) % tabell.length;
        }
        return sum;
    }

    public boolean checkNavn(String navn) {
        return (tabell[getIndexVerdi(navn)] != null);
    }

    public double getAvgKolliPrPerson() {
        return (double) antallKollisjoner / antallPersoner;
    }

    public boolean setInn(String navn) {
        if (tabell[getIndexVerdi(navn)] == null) {
            tabell[getIndexVerdi((navn))] = navn;
            antallPersoner++;
            return true;
        } else {
            antallKollisjoner++;
            return false;
        }
    }

    double getLastFaktoren() {
        return (double) antallPersoner / tabell.length;
    }

    void displayHash() {
        for (int i = 0; i < tabell.length; i++) {
            if (tabell[i] != null) {
                System.out.println(i + " --> " + tabell[i]);
            }
        }
    }
}
