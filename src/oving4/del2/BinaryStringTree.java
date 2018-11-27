package oving4.del2;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryStringTree {

    Node root;

    public void add(String value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, String word) {
        if (current == null) {
            return new Node(word);
        }

        if (word.compareTo(current.word) < 0) {
            current.left = addRecursive(current.left, word);
        } else if (word.compareTo(current.word) > 0) {
            current.right = addRecursive(current.right, word);
        } else {
            // value already exists
            return current;
        }

        return current;
    }


    public boolean containsNode(String word) {
        return containsNodeRecursive(root, word);
    }

    public void delete(String word) {
        root = deleteRecursive(root, word);
    }


    private boolean containsNodeRecursive(Node current, String word) {
        if (current == null) {
            return false;
        }
        if (word.equals(current.word)) {
            return true;
        }
        return (word.compareTo(current.word) < 0)
                ? containsNodeRecursive(current.left, word)
                : containsNodeRecursive(current.right, word);
    }


    private Node deleteRecursive(Node current, String word) {
        if (current == null) {
            return null;
        }

        if (word.equals(current.word)) {
            /* Har ingen barne node */
            if (current.left == null && current.right == null) {
                return null;
            }


            /* Har en barne node */
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            /* Har 2 barne node */

            String smallestValue = findSmallestValue(current.right);
            current.word = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;

        }
        if (word.compareTo(current.word) < 0) {
            current.left = deleteRecursive(current.left, word);
            return current;
        }
        current.right = deleteRecursive(current.right, word);
        return current;
    }


    private String findSmallestValue(Node root) {
        return root.left == null ? root.word : findSmallestValue(root.left);
    }


    /* Riktig rekkefølge */
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.word);
            traverseInOrder(node.right);
        }
    }

    /* Preordentraversering: Først rota, så venstre subtre (og dens venstre subtre) så høyre subtre */
    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print("" + node.word);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }


    public void printLineByLine() {
        printLineByLine(root);
    }


    private void printLineByLine(Node root) {
        if (root == null) {
            return;
        }
        final Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int space = 64;
        while (!queue.isEmpty()) {
            final int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node x = queue.remove();
                for (int n = 0; n < space; n++) System.out.print(" ");
                System.out.print(x.word);
                for (int n = 0; n < space; n++) System.out.print(" ");
                if (x.left != null) {
                    queue.add(x.left);
                }
                if (x.right != null) {
                    queue.add(x.right);
                }
            }
            space = space / 2;
            // new level
            System.out.println();
        }
    }


    /*Postordentraversering: Først venstre, så høyre så rota selv */
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.word);
        }
    }

    /* Nivåordentraversering: først rota, så barna så dems barn */
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.print(" " + node.word);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }
}