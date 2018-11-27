package oving13;

import java.util.Comparator;

class Connection {
    private int connection;
    private int length;
    private Connection nextConnection;

    Connection(int connection, int length) {
        this.connection = connection;
        this.length = length;
    }

    int getConnection() {
        return connection;
    }

    Connection getNextConnection() {
        return nextConnection;
    }

    public int getLength() {
        return length;
    }

    void addConnection(int connection, int weight) {
        if (nextConnection == null) nextConnection = new Connection(connection, weight);
        else nextConnection.addConnection(connection, weight);
    }
}

class LengthFromSourceComparator implements Comparator<Integer> {
    private int[] lengthFromSource;

    LengthFromSourceComparator(int[] lengthFromSource) {
        this.lengthFromSource = lengthFromSource;
    }

    @Override
    public int compare(Integer node1, Integer node2) {
        return lengthFromSource[node1] - lengthFromSource[node2];
    }
}

class LengthBetweenNodesComparator implements Comparator<Integer> {
    private int[] lengthFromSource;
    private int[] distanceTo;

    LengthBetweenNodesComparator(int[] distanceTo, int[] lengthFromSource) {
        this.distanceTo = distanceTo;
        this.lengthFromSource = lengthFromSource;
    }

    @Override
    public int compare(Integer node1, Integer node2) {
        return distanceTo[node1] + lengthFromSource[node1] - distanceTo[node2] - lengthFromSource[node2];
    }
}