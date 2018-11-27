package oving13;

import java.util.HashMap;
import java.util.Map;

class GraphPlaces {
    private Map<Integer, String> places = new HashMap<>();

    void addPlace(int node, String name) {
        places.put(node, name);
    }

    int getNode(String name) {
        for (Map.Entry<Integer, String> entry : places.entrySet()) {
            if (name.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return -1;
    }

    String getName(int node) {
        return places.get(node);
    }
}