package oving7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        Graph graph = getGraphFromFile("L7g5");
        graph.findStronglyConnectedComponents();
        System.out.println(graph);
    }

    private static Graph getGraphFromFile(String fileName) {
        String path = "src/oving7/graphs/" + fileName;
        Graph graph = null;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            graph = new Graph(fileName, Integer.parseInt(br.readLine().split(" ")[0]));
            String line = br.readLine();
            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, " ");
                graph.addKant(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graph;
    }
}
