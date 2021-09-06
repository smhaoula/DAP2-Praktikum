import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class GraphTest {

    /**
     * Loest das SSSP-Proplem mit Hilfe des Algortihmus von Bellman-Ford.
     *
     * @param g      der Graph
     * @param source id des Startknotes
     * @return Array mit Weglaengen; Element i gibt die Laenge eines kuerzesten
     * Weges von dem Knoten mit der id source zu dem Knoten mit id i an
     *
     */

    public static double[] sssp(Graph g, int source) {
        /* TODO */
        if (!g.getNodes().isEmpty()){
            int size = g.getNodes().size();

            for (Node n : g.getNodes()) {
                if (size < n.getId())
                    size = n.getId();
            }
            size++;

            double [] distance = new double[size];
            double [] tmp = new double[size];

            for (int i = 0;i < size;i++) {
                distance[i] = Double.POSITIVE_INFINITY;
            }
            distance[source] = 0;
            for (int i = 1;i < distance.length;i++){
                for (Node j : g.getNodes()){
                    if (j.getId() != source) {
                        tmp[j.getId()] = distance[j.getId()];
                        for (Edge k : j.getAdjList()) {
                            tmp[j.getId()] = Math.min(tmp[j.getId()], distance[k.getDst().getId()] + g.getCost(j,k.getDst()));
                        }
                    }
                }
                distance = tmp;
            }
            return distance;
        }
        throw new UnsupportedOperationException("Aufgabe 12.1 noch nicht bearbeitet!");
    }

    public static void main(String[] args) throws NumberFormatException, IOException, NoSuchElementException {
        if (args.length != 2) {
            fatal("Falsche Anzahl an Parametern");
        }

        Graph g = new Graph();
        try {
            g = Graph.fromFile(args[0]);
        } catch (IOException e) {
            fatal("Angegebene Datei konnte nicht gelesen werden.");
        } catch (IllegalArgumentException e) {
            fatal("Graph Datei konnte auf Grund eines Formatierungsfehlers nicht gelesen werden.");
        }

        if (g.getNodes().isEmpty()) {
            fatal("Leerer Graph.");
        }

        // Fuehre Bellman-Ford-Algorithmus aus
        int nodenumber = -1;
        try {
            nodenumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            fatal("Zweiter Parameter muss ein Integer sein");
        }
        if (g.getNode(nodenumber) == null) {
            fatal("Ungueltiger Startknoten angegeben: " + args[1]);
        }
        double[] minCost = sssp(g, nodenumber);
        ArrayList<Node> nodes = g.getNodes();
        Node s = g.getNode(nodenumber), e = s;
        double maxDist = 0d;
        for (Node n : nodes) {
            if (nodes.size() <= 20) {
                System.out.println("Abstand von Knoten " + n.getId() + " zu Knoten " + s.getId() + ": " + minCost[n.getId()]);
            }
            if (minCost[n.getId()] != Double.POSITIVE_INFINITY && minCost[n.getId()] > maxDist) {
                maxDist = minCost[n.getId()];
                e = n;
            }
        }
        System.out.println("Der maximale Abstand ist " + maxDist + " fuer Knoten " + e.getId());
    }

    public static void fatal(String message) {
        System.err.println("FEHLER: " + message);
        System.err.println("Aufruf: java GraphTest <filename> <nodenumber>");
        System.exit(1);
    }

}
