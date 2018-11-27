package oving5.del2;

import java.util.Date;
import java.util.HashMap;

public class Main {

    private static final int max = 6000;
    private static final int min = 1;

    private static int[] getTabell(int length) {
        int[] stocks = new int[length];
        for (int i = 0; i < length; i++) {
            stocks[i] = (int) (Math.random() * ((max - min) + 1)) + min;
        }
        return stocks;
    }


    private static void finnTidMinHash(HashTabell hashTabell, int[] tabell) {
        int runder = 1;
        double tid;
        Date slutt;
        Date start;
        start = new Date();
        do {
            for (int i = 0; i < tabell.length; i++) {
                hashTabell.insert(tabell[i]);
            }
            slutt = new Date();
        } while (slutt.getTime() - start.getTime() < 1000);
        tid = (double) (slutt.getTime() - start.getTime()) / runder;
        System.out.println("Tiden for min hashtabell tok: " + tid);
    }

    private static void finnTidJavaHash(HashMap hashTabell, int[] tabell) {
        int runder = 1;
        double tid;
        Date slutt;
        Date start;
        start = new Date();
        do {
            for (int i = 0; i < tabell.length; i++) {
                hashTabell.put(i, tabell[i]);
            }
            slutt = new Date();
        } while (slutt.getTime() - start.getTime() < 1000);
        tid = (double) (slutt.getTime() - start.getTime()) / runder;
        System.out.println("Tiden for Hashmap tok: " + tid);
    }

    public static void main(String[] args) {
        int[] tabell = getTabell(3000);
        HashMap hashMap = new HashMap();
        finnTidJavaHash(hashMap, tabell);

        HashTabell hashTabell = new HashTabell(4007);
        finnTidMinHash(hashTabell, tabell);

        //hashTabell.displayHash();
        //System.out.println("Antall kollisjoner: " + hashTabell);
        hashTabell.displayHash();
    }
}
