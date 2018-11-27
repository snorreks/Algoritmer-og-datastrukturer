package oving8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Graph {

    private final String NAME;
    private Node[] nodeList;
    private PriorityQueue<Node> priorityQueue;
    private int startNode;

    Graph(String name, int antNodes, int startNode) {
        this.startNode = startNode;
        NAME = name;
        nodeList = new Node[antNodes];
        for (int i = 0; i < antNodes; i++) {
            nodeList[i] = new Node();
            nodeList[i].id = i;
        }
        priorityQueue = new PriorityQueue<>(antNodes, (fraNode, tilNode) -> (fraNode.forgjenger.vekt - tilNode.forgjenger.vekt));
    }

    void addEdge(int fra, int til, int vekt) {
        nodeList[fra].edge = new Edge(nodeList[til], nodeList[fra].edge, vekt);
    }

    private void forkort(Node n, Edge edge) {
        Node fra = n.forgjenger;
        Node til = edge.tilNode.forgjenger;

        if (til.vekt > fra.vekt + edge.vekt) {
            til.vekt = fra.vekt + edge.vekt;
            til.forgjenger = n;
            priorityQueue.remove(edge.tilNode);
            priorityQueue.add(edge.tilNode);
        }
    }

    private void initForgjenger(Node s) {
        for (int i = nodeList.length; i-- > 0; ) {
            nodeList[i].forgjenger = new Node();
        }
        s.forgjenger.vekt = 0;
    }

    void regnUtDijkstra() {
        Node noden = nodeList[startNode];
        initForgjenger(noden);
        priorityQueue.addAll(new ArrayList<>(Arrays.asList(nodeList)));

        for (int i = nodeList.length; i > 1; --i) {
            Node n = priorityQueue.poll();
            for (Edge edge = n.edge; edge != null; edge = edge.nestEdge) {
                forkort(n, edge);
            }
        }
    }

    void skrivUtGraf() {
        String print = "Graf: " + NAME + ", startnode: " + startNode + "\n";
        print += "Node    forgjenger    distanse";
        System.out.println(print);
        for (int i = 0; i < nodeList.length; i++) {
            if (i != startNode) {
                String distanse = "                       nåes ikke";
                if (nodeList[i].forgjenger.vekt != 100000000) {
                    distanse = "             " + nodeList[i].forgjenger.vekt;
                }
                if (nodeList[i].forgjenger.forgjenger != null) {
                    System.out.println(nodeList[i].id + "            " + nodeList[i].forgjenger.forgjenger.id + distanse);
                } else {
                    System.out.println(nodeList[i].id + distanse);
                }
            } else {
                System.out.println(startNode + "          start           0");
            }
        }
    }

    private class Node {
        private int id;
        private Edge edge;
        private Node forgjenger;
        private int vekt = 100000000; //Kunne brukt Integer.MAX_VALUE, men da ble det også negativt noen ganger.
    }

    private class Edge {
        int vekt;
        private Edge nestEdge;
        private Node tilNode;

        Edge(Node tilNode, Edge nestEdge, int vekt) {
            this.tilNode = tilNode;
            this.nestEdge = nestEdge;
            this.vekt = vekt;
        }
    }
}