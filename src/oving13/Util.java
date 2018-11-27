package oving13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Util {

    static void displayInfo(String algorithm, int totalNodesProcessed, int antNodesBetween, int length, long totalTime) {
        System.out.println(algorithm);
        System.out.println("Antall noder trengs for å plukke fra køen: " + totalNodesProcessed);
        System.out.println("Antall noder mellom " + Main.FROM + " og " + Main.TO + ": " + antNodesBetween + ".");
        System.out.println("Tid reiseruta tar:                         " + Math.floor(length / 100.0) + "s, " + Math.floor(length / 6000.0) + " min, eller " + Math.floor(length / 36000.0) / 10 + " h.");
        System.out.println("Tiden for å beregne algoritmen:            " + totalTime + " ms.");
    }

    static GraphEdges readEdgesFile(String filename) {
        String line;
        String[] line2;
        GraphEdges graphEdges = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            graphEdges = new GraphEdges(Integer.parseInt(br.readLine().trim().split("\\s")[0]));
            line = br.readLine();
            while (line != null) {
                line2 = line.trim().split("\\s+");
                graphEdges.addNode(Integer.parseInt(line2[0]), Integer.parseInt(line2[1]), Integer.parseInt(line2[2]));
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            ioe.printStackTrace();
        }

        return graphEdges;
    }

    static GraphNodes readNodesFile(String filename) {
        String line;
        String[] line2;
        GraphNodes graphNodes = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            graphNodes = new GraphNodes(Integer.parseInt(br.readLine().trim()));
            line = br.readLine();

            while (line != null) {
                line2 = line.trim().split("\\s+");
                graphNodes.addLocation(Integer.parseInt(line2[0]), Double.parseDouble(line2[1]), Double.parseDouble(line2[2]));
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return graphNodes;
    }

    static GraphPlaces readPlacesFile(String filename) {
        String line;
        String[] line2;
        GraphPlaces graphPlaces = new GraphPlaces();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            /*--- HashMap så trenger ikke å vite antall plasser, hopp over antall plasser linja ---*/
            br.readLine();
            line = br.readLine();
            while (line != null) {
                line2 = line.trim().split("\\s+");
                graphPlaces.addPlace(Integer.parseInt(line2[0]), line2[2].substring(1, line2[2].length() - 1));
                line = br.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return graphPlaces;
    }
}