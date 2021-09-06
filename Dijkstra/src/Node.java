import java.util.ArrayList;

public class Node {

    private ArrayList<Edge> edgeList;
    private int id;

    public Node(int id){
        this.id = id;
        edgeList = new ArrayList<Edge>();
    }

    public ArrayList<Edge> getList() {
        return edgeList;
    }

    public int getId() {
        return id;
    }

    public void addEdge(Node dst, int weight){
        Edge edge = new Edge(this,dst,weight);
        edgeList.add(edge);
    }

    public boolean equals (Object other){
        Node node = (Node) other;
        return this.id == node.id;
    }


}
