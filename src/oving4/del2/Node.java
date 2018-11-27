package oving4.del2;

public class Node {
    String word;
    Node left;
    Node right;

    Node(String value) {
        this.word = value;
        right = null;
        left = null;
    }
}