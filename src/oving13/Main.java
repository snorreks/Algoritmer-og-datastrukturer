package oving13;

public class Main {

    final static String FROM = "Sarpsborg";
    final static String TO = "Stockholm";

    public static void main(String[] args) {
        GPS gps = new GPS("kanter.txt", "noder.txt", "interessepkt.txt");
        System.out.println();
        gps.navigateDijkstras();
        System.out.println();
        gps.navigateAStar();
        System.out.println();
        gps.printCords();
    }
}