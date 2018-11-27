package oving8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Graph graph = getGraphFromFile("vg1", 0);
        graph.regnUtDijkstra();
        graph.skrivUtGraf();
    }

    private static Graph getGraphFromFile(String fileName, int startNode) {
        String path = "src/oving8/graphs/" + fileName;
        Graph graph = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            graph = new Graph(fileName, Integer.parseInt(br.readLine().split(" ")[0]), startNode);
            String line = br.readLine();
            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                graph.addEdge(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }
}