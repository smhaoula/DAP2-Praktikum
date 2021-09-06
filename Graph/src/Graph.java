import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

class Graph {

	private ArrayList<Node> nodes = new ArrayList<Node>();
	private double[][] weights;
	private int cap = 4;

	/**
	 * Erstellt einen leeren, (un)gerichteten Graphen mit einer Grundkapazitaet
	 * @param cap Grundkapazitaet fuer die Anzahl der Knoten
	 */
	public Graph(int cap){
		if (cap > 0) this.cap = cap;
		nodes = new ArrayList<Node>(this.cap);
		weights = new double[this.cap][this.cap];
	}

	/**
	 * Erstellt einen leeren, ungerichteten Graphen
	 */
	public Graph(){
		this(0);
	}

	/**
	 * Check if a node with the given id is present within the graph.
	 * 
	 * @param int id of the node in question
	 * @return true if a node with the given id is present, false otherwise
	 */
	public boolean contains(int id) {
		Predicate<Node> idPredicate = node -> node.getId() == id;
		return nodes.stream().anyMatch(idPredicate);

		/** Old-school, pre Java 8: */
		/*
		 * for (Node n : this.nodes) { if (n.getId() == id) { return true; } } return
		 * false;
		 */
	}

	/**
	 * Add a node to the graph if the id is still free.
	 * 
	 * @param int id of the new node
	 * @return true if node is successfully added, false otherwise
	 */
	public boolean addNode(int id) throws IllegalArgumentException {
		if (!this.contains(id)) {
			Node n = new Node(id);
			this.nodes.add(n);

			if (id >= cap){
				cap *= 2;
			}
			double[][] newCosts = new double[cap][cap];
			int oldCap = weights.length;
			for (int i = 0; i< oldCap; i++){
				System.arraycopy(weights[i], 0, newCosts[i], 0, oldCap);
			}
			weights = newCosts;

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gibt eine Liste aller Kanten im Graphen zurueck
	 *
	 * @return ArrayList containing all edges in the graph
	 */
	public ArrayList<Edge> getEdges() {
		ArrayList<Edge> edges = new ArrayList<Edge>();

		for (Node node: nodes){
			for (Edge edge: node.getAdjList()) {
				Edge siblingEdge = edge.getSiblingEdge();
				if (!edges.contains(edge.getSiblingEdge())){
					edges.add(edge);
				}
			}
		}

		return edges;
	}

	/**
	 * Gibt die Liste aller Knoten im Graphen zurueck
	 *
	 * @return ArrayList containing all nodes in the graph.
	 */
	public ArrayList<Node> getNodes() {
		return this.nodes;
	}

	/**
	 * Get the node with the given id from the graph.
	 * 
	 * @param int id of the node in question
	 * @return the Node if (it is present), null otherwise
	 */
	public Node getNode(int id) {
		for (Node n : this.nodes) {
			if (n.getId() == id) {
				return n;
			}
		}
		return null;
	}

	/**
	 * Gibt die Kosten einer Kante zurueck
	 * @param src ID des Startknotens
	 * @param dest ID des Endknotens
	 * @return die Kosten der Kante oder -1, falls die Knoten oder die Kante nicht exisitieren
	 */
	public double getCost(int src, int dest){
		if (src >= weights.length || dest >= weights[src].length ) return -1;
		return (weights[src][dest] > 0? weights[src][dest]: -1);
	}

	/**
	 * Gibt die Kosten einer Kante zurueck
	 * @param v Startknoten
	 * @param u Endknoten
	 * @return die Kosten der Kante oder -1, falls die Knoten oder die Kante nicht exisitieren
	 */
	public double getCost(Node v, Node u){
		return getCost(v.getId(),u.getId());
	}

	/**
	 * Gibt eine Kopie der Kostenmatrix zurueck
	 * @return Kopie der Kostenmatrix
	 */
	public double[][] getWeights(){
		double[][] ret = new double[cap][cap];
		for(int i = 0; i < cap;i++){
			System.arraycopy(weights[i], 0, ret[i], 0, cap);
		}
		return ret;
	}

	/**
	 * Add an Edge between the nodes with the ids srcid and dstid if both are
	 * present
	 * 
	 * @param int source id
	 * @param int destination id
	 * @return true if the edge is successfully added, false otherwise
	 */
	public boolean addEdge(int srcid, int dstid, int weight) throws IllegalArgumentException {
		if (!(this.contains(srcid)) || !(this.contains(dstid)) || !(this.getNode(srcid).getAdjacentEdge(this.getNode(dstid)) == null)) {
			return false;
		} else {
			this.getNode(srcid).addEdge(new Edge(this.getNode(srcid), this.getNode(dstid), weight));
			this.getNode(dstid).addEdge(new Edge(this.getNode(dstid), this.getNode(srcid), weight));
			weights[srcid][dstid] = weight;
			weights[dstid][srcid] = weight;
			return true;
		}
	}

	/**
	 * Read a graph from a .graph file using the format: Each line contains an edge,
	 * specified by source id and destination id, seperated by a comma. Example: 1,2
	 * 1,3 2,3
	 * 
	 * @param String filename/path of a .graph file
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @returns Graph built from the file
	 */
	public static Graph fromFile(String filename) throws  IllegalArgumentException, IOException {
		BufferedReader file = new BufferedReader(new FileReader(filename));
		String line;
		StringTokenizer st;
		Set<Integer> nodes = new HashSet<>();
		ArrayList<Edge> edges = new ArrayList<>();

		while ((line = file.readLine()) != null) {
			st = new StringTokenizer(line, ",");
			if (st.countTokens() != 3) { throw new IllegalArgumentException(); }
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			nodes.add(src);
			nodes.add(dest);
			edges.add(new Edge(new Node(src), new Node(dest), weight));
		}
		file.close();

		Graph g = new Graph(nodes.size());

		for (Integer nodeID: nodes) {
			g.addNode(nodeID);
		}
		for (Edge edge: edges) {
			g.addEdge(edge.getSrc().getId(), edge.getDst().getId(), edge.getWeight());
		}

		return g;
	}

	/**
	 * Return string representation of a graph, listing each of its nodes with a
	 * list of outgoing edges
	 * 
	 * @return String representation of the graph
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Node n : nodes) {
			StringJoiner joiner = new StringJoiner(", ");
			result.append("Knoten ").append(n.getId()).append(" verbunden zu: ");
			for (Edge e : n.getAdjList()) {
				joiner.add(e.toStringTarget());
			}
			result.append(joiner.toString());
			result.append('\n');
		}
		return result.toString();
	}

	/**
	 * @return int number of nodes in the graph
	 */
	public int numNodes() {
		return this.nodes.size();
	}

}
