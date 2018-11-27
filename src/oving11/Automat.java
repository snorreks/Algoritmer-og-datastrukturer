package oving11;

class Automat {

    private char[] inputalfabet;
    private int[] akseptTilstander;
    private int[] tilstander;
    private int[][] nesteTilstandTabell;

    Automat(char[] inputalfabet, int[] akseptTilstander, int[][] nesteTilstandTabell) {
        this.inputalfabet = inputalfabet;
        this.akseptTilstander = akseptTilstander;
        this.nesteTilstandTabell = nesteTilstandTabell;
        this.tilstander = new int[nesteTilstandTabell.length];
        for (int i = 0; i < nesteTilstandTabell.length; i++) {
            this.tilstander[i] = i;
        }
    }

    int findAlfabetIndexAt(char input) {
        for (int i = 0; i < inputalfabet.length; i++) {
            if (inputalfabet[i] == input) {
                return i;
            }
        }
        return -1;
    }

    boolean sjekkInput(char[] input) {
        int forrigeTilstandIndex = 0;
        for (int i = 1; i <= input.length; i++) {
            forrigeTilstandIndex = nesteTilstandTabell[forrigeTilstandIndex][findAlfabetIndexAt(input[i - 1])];
            if (i == input.length) {
                for (int akseptTilstand : akseptTilstander) {
                    if (akseptTilstand == nesteTilstandTabell[forrigeTilstandIndex][findAlfabetIndexAt(input[i - 1])]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Test {
    public static void main(String[] args) {
        Automat automat1 = new Automat(new char[]{'0', '1'}, new int[]{3}, new int[][]{
                {1, 2},
                {1, 3},
                {2, 2},
                {3, 2}});

        Automat automat2 = new Automat(new char[]{'a', 'b'}, new int[]{4}, new int[][]{
                {1, 2},
                {3, 4},
                {4, 3},
                {3, 3},
                {4, 4}});
        System.out.println("Automat 1:");
        System.out.println("Inputen 010 er " + (automat1.sjekkInput(new char[]{'0', '1', '0'}) ? "legit!" : "IKKE legit!"));
        System.out.println("Inputen 111 er " + (automat1.sjekkInput(new char[]{'1', '1', '1'}) ? "legit!" : "IKKE legit!"));
        System.out.println("Inputen 010110 er " + (automat1.sjekkInput(new char[]{'0', '1', '0', '1', '1', '0'}) ? "legit!" : "IKKE legit!"));
        System.out.println("Inputen 001000 er " + (automat1.sjekkInput(new char[]{'0', '0', '1', '0', '0', '0'}) ? "legit!" : "IKKE legit!"));


        System.out.println("\nAutomat 2:");
        System.out.println("Inputen abbb er " + (automat2.sjekkInput(new char[]{'a', 'b', 'b', 'b'}) ? "legit!" : "IKKE legit!"));
        System.out.println("Inputen aaab er " + (automat2.sjekkInput(new char[]{'a', 'a', 'a', 'b'}) ? "legit!" : "IKKE legit!"));
        System.out.println("Inputen babab er " + (automat2.sjekkInput(new char[]{'b', 'a', 'b', 'a', 'b'}) ? "legit!" : "IKKE legit!"));
    }
}