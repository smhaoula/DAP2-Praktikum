import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Graph {

    private ArrayList<Node> knoten;

    public Graph(){
        knoten = new ArrayList<Node>();
    }

    public boolean contains(int id){
        return knoten.indexOf(new Node(id)) > -1;
    }

    public boolean addNode(int id){
       if (this.contains(id))
           return false;
       knoten.add(new Node(id));
       return true;
    }

    public Node getNode(int id){
        int index = knoten.indexOf(new Node(id));
        if (index > -1) return knoten.get(index);
        else return null;
    }

    public boolean addEdge(int src, int dst, int weight){
        if (this.contains(src) && this.contains(dst)){
            Node srcN = knoten.get(knoten.indexOf(new Node(src)));
            Node dstN = knoten.get(knoten.indexOf(new Node(dst)));
            srcN.addEdge(dstN,weight);
            return true;
        }
        return false;
    }

    public ArrayList<Node> getNodList(){
        return knoten;
    }

    public boolean isEmpty(){
        return knoten.size() == 0;
    }

    public static Graph fromFile(String filepath) throws FileNotFoundException, IOException {
        Graph graph = new Graph();
        BufferedReader datei = new BufferedReader(new FileReader(filepath));
        String line = datei.readLine();
        StringTokenizer st;
        int src,dst;
        int weight;
        while (line != null){
            st = new StringTokenizer(line, ",");
            src = Integer.parseInt(st.nextToken());
            dst = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            graph.addNode(src);
            graph.addNode(dst);
            graph.addEdge(src,dst,weight);
            line = datei.readLine();
            assert !graph.isEmpty() : "Graph ist leer geblieben";
        }

        datei.close();
        return graph;
    }

    @Override
    public String toString(){
        String s = "";
        for (int i = 0;i < knoten.size();i++) {
            String s1 = "";
            if (knoten.get(i) != null) {
                ArrayList<Edge> edge = knoten.get(i).getList();
                boolean a = true;
                for (int j = 0; j < edge.size(); j++) {
                    if (edge.get(j) != null) {
                        if (a) {
                            s1 += edge.get(j).getDst().getId() + " (" + edge.get(j).getWeight() + ")";
                            a = false;
                        } else {
                            s1 += "," + edge.get(j).getDst().getId() + " (" + edge.get(j).getWeight() + ")";
                        }
                    }
                }
                s += "Knoten " + knoten.get(i).getId() + " verbunden zu :" + s1 + "\n";
            }
        }
        return s;
    }

}













