package oving13;

class GPS {
    private final static String PATH = "src/oving13/res/";
    private GraphEdges graphEdges;
    private GraphPlaces graphPlaces;
    private GraphNodes graphNodes;
    private int[] route;

    GPS(String edgeName, String nodeName, String placesName) {
        graphEdges = Util.readEdgesFile(PATH + edgeName);
        graphPlaces = Util.readPlacesFile(PATH + placesName);
        graphNodes = Util.readNodesFile(PATH + nodeName);
    }

    void navigateAStar() {
        AStarAlgo aStarAlgo = new AStarAlgo();
        int[] route = aStarAlgo.navigate(graphPlaces.getNode(Main.FROM), graphPlaces.getNode(Main.TO), graphNodes, graphEdges.nodes);
        displayRoute(route);

        System.out.println();
        System.out.println(isEqual(route) ? "A* har like ruter som Dijkstras!" : "A* har IKKE!!!! like ruter som Dijkstras!");
    }

    void navigateDijkstras() {
        DijkstrasAlgo dijkstrasAlgo = new DijkstrasAlgo();
        route = dijkstrasAlgo.navigate(graphPlaces.getNode(Main.FROM), graphPlaces.getNode(Main.TO), graphEdges.nodes);
        displayRoute(route);
    }

    private void displayRoute(int[] route) {
        System.out.println("Ruten går gjennom:");
        for (int aRoute : route) {
            String name = graphPlaces.getName(aRoute);
            if (name != null) System.out.println(name);
        }
    }

    private boolean isEqual(int[] otherRoute) {
        for (int i = 0; i < route.length; i++) {
            if (route[i] != otherRoute[i]) return false;
        }
        return true;
    }

    void printCords() {
        for (int aRoute : route) {
            System.out.println(graphNodes.getLongLat(aRoute)[0] + "  " + graphNodes.getLongLat(aRoute)[1]);
        }
    }
}