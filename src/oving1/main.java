package oving1;

import java.util.Date;

public class main {
    private static String findSellandBuyDay(int[] tab) {        // 0: deklerasjon
        int min = 0;                                        // 1: tilordning
        int max = 0;                                        // 1: tilordning
        int maxDifference = 0;                              // 1: tilordning
        int tempDifference = 0;                             // 1: tilordning
        for (int i = 0; i < tab.length; i++) {              // 1+2n:  1 x tilordning, n x sammenligning, n x inkr
            int temp = tab[i];                              // 1: tilordning, n tabelloppslag
            for (int j = i + 1; j < tab.length; j++) {      // 1+2n: 2 x tilordning og addisjoner, n x sammenligning, n x inkr
                if (temp < tab[j]) {                        // n: sammenligning
                    tempDifference = tab[j] - temp;         // 2n: n diffinsjon, n tabelloppslag
                }                                           // 0
                if (tempDifference > maxDifference) {       // n: sammenligning
                    maxDifference = tempDifference;         // 1: tilordning
                    min = i;                                // 1: tilordning
                    max = j;                                // 1: tilordning
                }                                           // 0
            }                                               // 0
        }                                                   // 0
        return "Kjøp på dag nr " + (min + 1) + ", og " +    // 3: 1 return, 2 addisjon
                "selg på dag nr " + (max + 1) + ".";
    }

    private static int[] getStocks(int length) {
        int max = 20;
        int min = -10;
        int[] stocks = new int[length];
        for (int i = 0; i < stocks.length; i++) {
            stocks[i] = (int) (Math.random() * ((max - min) + 1)) + min;
        }
        for (int i = 0; i < stocks.length; i++) {
            System.out.print(stocks[i] + ",");
        }
        return stocks;
    }

    public static void main(String[] args) {
        int[] stocks = getStocks(2000);
        String r;
        Date start = new Date();
        int runder = 0;
        double tid;
        Date slutt;
        do {
            r = findSellandBuyDay(stocks);
            slutt = new Date();
            ++runder;
        } while (slutt.getTime() - start.getTime() < 1000);
        tid = (double)
                (slutt.getTime() - start.getTime()) / runder;
        System.out.println("\nMillisekund pr. runde:" + tid);
        System.out.println(r);
    }
}
