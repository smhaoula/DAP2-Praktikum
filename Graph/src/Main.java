import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException, NoSuchElementException {
        if (args.length != 2) {
            GraphTest.fatal("Falsche Anzahl an Parametern");
        }

        Graph g = new Graph();
        try {
            g = Graph.fromFile(args[0]);
        } catch (IOException e) {
            GraphTest.fatal("Angegebene Datei konnte nicht gelesen werden.");
        } catch (IllegalArgumentException e) {
            GraphTest.fatal("Graph Datei konnte auf Grund eines Formatierungsfehlers nicht gelesen werden.");
        }

        if (g.getNodes().isEmpty()) {
            GraphTest.fatal("Leerer Graph.");
        }

        // Fuehre Bellman-Ford-Algorithmus aus
        //GraphTest.sssp(g,Integer.parseInt(args[1]));


        int nodenumber = -1;
        try {
            nodenumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            GraphTest.fatal("Zweiter Parameter muss ein Integer sein");
        }
        if (g.getNode(nodenumber) == null) {
            GraphTest.fatal("Ungueltiger Startknoten angegeben: " + args[1]);
        }
        double[] minCost = GraphTest.sssp(g, nodenumber);
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
/*
    public static void main(String[] args) {
        try {
            if (args.length == 2) {
                String pfad = args[0];
                int source = Integer.parseInt(args[1]);
                Graph graph = Graph.fromFile(pfad);
                System.out.println(GraphTest.sssp(graph,source).toString());
            }
        }catch (FileNotFoundException notFoundException){
            System.out.println("FEHLER: Datei wurde nicht gefunden. Bitte geben Sie einen gueltigen Pfad an.");
        }catch (IOException inputoutputException){
            System.out.println(inputoutputException.getMessage());
        }catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }
    */
}