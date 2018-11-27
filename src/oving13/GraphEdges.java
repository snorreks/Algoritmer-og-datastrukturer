package oving13;

class GraphEdges {
    Connection[] nodes;

    GraphEdges(int size) {
        nodes = new Connection[size];
    }

    void addNode(int index, int connection, int weight) {
        if (nodes[index] == null) nodes[index] = new Connection(connection, weight);
        else nodes[index].addConnection(connection, weight);
    }
}