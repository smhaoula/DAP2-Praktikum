public class Edge {

    private Node src;
    private Node dst;
    private int weight;

    public Edge(Node src,Node dst,int weight){
        if (weight > 0){
            this.src = src;
            this.dst = dst;
            this.weight = weight;
        }
    }

    public Node getSrc() {
        return src;
    }

    public Node getDst() {
        return dst;
    }

    public int getWeight() {
        return weight;
    }
}
