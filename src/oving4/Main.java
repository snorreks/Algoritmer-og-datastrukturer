package oving4;

public class Main {

    private static BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }

    public static void main(String[] args) {
        BinaryTree b = createBinaryTree();
        System.out.println(b.containsNode(5));
        b.delete(5);
        System.out.println(b.containsNode(5));
    }
}
