package oving4.del2;

public class Main {

    private static BinaryStringTree createBinaryTree() {
        BinaryStringTree bt = new BinaryStringTree();

        bt.add("hode");
        bt.add("ben");
        bt.add("legg");
        bt.add("arm");
        bt.add("hake");

        bt.add("albue");
        bt.add("tann");
        bt.add("tå");


        return bt;
    }

    public static void main(String[] args) {
        BinaryStringTree b = createBinaryTree();
        b.printLineByLine();
    }
}