package oving7;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final List<Node> neighbors = new ArrayList<>();
    String nodeData;
    boolean isVisited = false;
    Node next = null;
    private String name;

    Node(String nodeData) {
        this.nodeData = nodeData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public void addNeighbors(List<Node> neighbors) {
        this.neighbors.addAll(neighbors);
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }
}
