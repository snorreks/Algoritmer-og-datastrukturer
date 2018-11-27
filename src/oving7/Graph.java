package oving7;

import java.util.*;

/**
 * Klassen for å representere en rettet graf med å bruke en kantListe
 */

/**
 * Vertex = node
 */
class Graph {
    private final String NAME;
    private int antNodes;
    private LinkedList<Integer> kantListe[];
    private ArrayList<List<Integer>> stronglyConnectedComponentsList = new ArrayList<>();

    /**
     * Konstruktør lager linkalista med størrelse lik antall noder
     */
    Graph(String navn, int antallNodes) {
        NAME = navn;
        this.antNodes = antallNodes;
        kantListe = new LinkedList[antallNodes];
        for (int i = 0; i < antallNodes; ++i)
            kantListe[i] = new LinkedList<>();
    }

    /**
     * Add kantene i grafen
     */
    void addKant(int franode, int tilnode) {
        kantListe[franode].add(tilnode);
    }

    /**
     * Rekrusiv Depth-first search funksjon, som brukes til den omvendte grafen
     */
    private void DFS2(int fraNode, boolean visited[], List<Integer> results) {
        /** Markerer den selecta noden som besøkt, så i List<Integer> result lagerer bare unike noder */

        visited[fraNode] = true;
        results.add(fraNode);

        /** Bruker lambda funksjon for å finne om kanListe[fraNode] has next,
         * sjekker om noden er besøkt fra før, for å bare få unike noder i den sterke sammensatte lista
         * Så lenge man bruker DFS av en fraNode i den sterke sammensatte komponenten så finner den alle nodene i den komponenen den er en del av*/
        kantListe[fraNode].iterator().forEachRemaining(e -> {
            if (!visited[e]) DFS2(e, visited, results);
        });
    }

    /**
     * Lager den omvendte grafen som retunerer revers / transpose av Hoved-grafen
     */
    private Graph getTranspose() {
        Graph g = new Graph(NAME, antNodes);
        /** Sjekk kantLista for hver node, og hvis du det fantes en kant fra  x -> y så gjør om slik at y -> x
         *  Bytter å¨kanLista i den andre grafen g, så det blir en kant fra y til x, når det egt er motsatt i hovedgrafen*/
        for (int n = 0; n < antNodes; n++) {
            /** Rekursiv funksjon for alle nabonodene til noden n*/
            for (Iterator<Integer> i = kantListe[n].listIterator(); i.hasNext(); )
                g.kantListe[i.next()].add(n);
        }
        return g;
    }

    /**
     * Tar Rekrusiv Depth-first søk av fraNoda, og lagrer finished time i prioerieteringsLista
     */
    private void fyllPrioriteringsListaDFS(int fraNode, boolean visited[], Stack<Integer> prioriteringsListe) {
        /** Marker fraNoda som true /marker */
        visited[fraNode] = true;

        /** Rekrusiv funksjon for alle nabo nodene
         *  Bruler lambda for å finne has next, og sjekker alle nodene som ikke har blitt sjekka som er nabonoder til fraNode
         *  Finner alle mulige veier noden kan ta til noden ikke lenger kan gå til en ny node som ikke er sjekket fra før*/
        kantListe[fraNode].iterator().forEachRemaining(e -> {
            if (!visited[e]) fyllPrioriteringsListaDFS(e, visited, prioriteringsListe);
        });

        /** All veier fra fraNode har blitt gått gjennom og nå kan vi adde noden i prioriterings stacken */
        prioriteringsListe.push(fraNode);
    }

    /**
     * Hoved funksjonen for å finne alle sterke komponenter og lagrer den i stronglyConnectedComponentsList lista
     */
    void findStronglyConnectedComponents() {
        Stack<Integer> prioriteringsListe = new Stack<>();

        /** 1. Markerer alle nodene som ikke besøkt (For first DFS) */
        boolean visited[] = new boolean[antNodes];
        Arrays.fill(visited, false);

        /** 2. Kjør DFS (dybde først trer) og fyller inn nodene i en prioeriteringsListe
         *  sortert på synkende ferdig tid */
        for (int i = 0; i < antNodes; i++)
            if (!visited[i])
                fyllPrioriteringsListaDFS(i, visited, prioriteringsListe);

        /** 3. Lager den omvendte grafen */
        Graph gr = getTranspose();

        /** 4. Markerer alle nodene som ikke besøkt (For second DFS) */
        Arrays.fill(visited, false);

        /** 5. Tøm prioriteringslista og kjør DFS2 på alle nodene i den omvendte grafen
         * og fyll alle nodene inn i sccLista (strong connected components List) */
        while (!prioriteringsListe.empty()) {
            /** Hent en node øverst fra stacken */
            int fraNode = prioriteringsListe.pop();

            /** Hvis noden ikke har vært besøkt fra før så kjør DFC og legg den inn i lista. */
            if (!visited[fraNode]) {
                List<Integer> results = new ArrayList<>();
                gr.DFS2(fraNode, visited, results);
                stronglyConnectedComponentsList.add(results);
            }
        }
    }

    /**
     * Printer ut slik oppgaven ber om, hvis SCCLista er større enn 100 så bare si hvor stor lista er
     */
    public String toString() {
        StringBuilder print = new StringBuilder("Grafen " + NAME + " har " + stronglyConnectedComponentsList.size() + " sterkt sammenhengende komponenter.\n" +
                "Komponent              Noder i komponenten\n");
        if (stronglyConnectedComponentsList.size() >= 100) return print.toString();
        for (int i = 0; i < stronglyConnectedComponentsList.size(); i++) {
            print.append((i + 1) + "                      " + stronglyConnectedComponentsList.get(i) + "\n");
        }
        return print.toString();
    }
}