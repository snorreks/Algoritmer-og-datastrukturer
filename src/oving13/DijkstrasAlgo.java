package oving13;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class DijkstrasAlgo {

    int[] navigate(int fromNode, int toNode, Connection[] nodes) {
        long startTime = System.currentTimeMillis();

        int[] lengthFromStart = new int[nodes.length];
        int[] cameFrom = new int[nodes.length];

        LengthFromSourceComparator comparator = new LengthFromSourceComparator(lengthFromStart);
        PriorityQueue<Integer> queue = new PriorityQueue<>(nodes.length, comparator);

        for (int i = 0; i < nodes.length; i++) {
            lengthFromStart[i] = 10000000;
            cameFrom[i] = 10000000;
        }

        lengthFromStart[fromNode] = 0;
        cameFrom[fromNode] = -2;

        queue.add(fromNode);

        int nodeIndex, totalLength, totalNodesProcessed = 0, getConnection;
        Connection connection;

        while (!queue.isEmpty()) {

            nodeIndex = queue.poll();

            if (nodeIndex == toNode) break;

            connection = nodes[nodeIndex];
            totalNodesProcessed++;

            while (connection != null) {

                totalLength = lengthFromStart[nodeIndex] + connection.getLength();
                getConnection = connection.getConnection();

                if (totalLength < lengthFromStart[getConnection]) {
                    lengthFromStart[getConnection] = totalLength;
                    cameFrom[getConnection] = nodeIndex;

                    if (toNode == getConnection) queue.remove(getConnection);
                    queue.add(getConnection);
                }

                connection = connection.getNextConnection();
            }
        }

        int traceBackNode = toNode;
        List<Integer> passedNodes = new ArrayList<>();
        passedNodes.add(0, toNode);

        while (cameFrom[traceBackNode] != -2) {
            passedNodes.add(0, traceBackNode);
            traceBackNode = cameFrom[traceBackNode];
        }

        long totalTime = System.currentTimeMillis() - startTime;

        Util.displayInfo("Dijkstras - Algorithm:", totalNodesProcessed, passedNodes.size() - 1, lengthFromStart[toNode], totalTime);

        return passedNodes.stream().mapToInt(i -> i).toArray();
    }
}