package oving2;

import java.util.Date;

public class main {

    private static double oppgave1(double x, int n) {
        if (n > 0) return (x * oppgave1(x, n - 1));
        else return 1;
    }

    private static double oppgave2(double x, int n) {
        if (n % 2 != 0) return x * oppgave2(x * x, (n - 1) / 2);
        else if (n != 0) {
            return oppgave2(x * x, n / 2);
        } else {
            return 1;
        }
    }

    private static void finnTid(double x, int n, int metode) {
        double r;
        int runder = 0;
        double tid;
        Date slutt;
        Date start;
        if (metode == 0) {
            start = new Date();
            do {
                r = oppgave1(x, n);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
        } else if (metode == 1) {
            start = new Date();
            do {
                r = oppgave2(x, n);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
        } else {
            start = new Date();
            do {
                r = Math.pow(x, n);
                slutt = new Date();
                ++runder;
            } while (slutt.getTime() - start.getTime() < 1000);
        }
        tid = (double) (slutt.getTime() - start.getTime()) / runder;
        System.out.println(r);
        System.out.println("Tiden tok: " + tid);
    }

    public static void main(String[] args) {
        System.out.println("Oppgave 1");
        finnTid(1.1, 500, 0);
        System.out.println("\nOppgave 2");
        finnTid(1.1, 500, 1);
        System.out.println("\nMath pow");
        finnTid(1.1, 500, 2);
    }
}