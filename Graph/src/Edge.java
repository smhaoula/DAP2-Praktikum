
class Edge implements Comparable<Edge> {
	private Node src;
	private Node dst;
	private int weight;

	/**
	 * Create a new Edge
	 * 
	 * @param source      Node of the edge
	 * @param destination Node of the edge
	 */
	public Edge(Node src, Node dst, int weight) {
		this.src = src;
		this.dst = dst;
		if (weight < 1) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
	}

	/**
	 * @return weight of edge
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Setzt das Gewicht der Kante auf den angegebenen Wert
	 *
	 * @param weight
	 */
	public void setWeight(int weight) {
		if (weight < 1) {
			throw new IllegalArgumentException();
		}
		this.weight = weight;
	}

	/**
	 * @return source Node
	 */
	public Node getSrc() {
		return this.src;
	}

	/**
	 * Gibt die Referenz auf die entgegengerichtete Kante aus der Adjazenzliste des Nachbarknoten zurueck
	 *
	 * @return
	 */
	public Edge getSiblingEdge() {
		return this.dst.getAdjacentEdge(this.src);
	}

	/**
	 * @return destination Node
	 */
	public Node getDst() {
		return this.dst;
	}

	/**
	 * Nuetzlich zum sortieren von Kanten nach Kantengewicht
	 *
	 * @param other
	 * @return
	 */
	@Override
	public int compareTo(Edge other) {
		return Integer.compare(this.getWeight(), other.getWeight());
	}
	
	/**
	 * @return a string representation of the edge (without src)
	 */
	public String toStringTarget() {
		StringBuilder sb = new StringBuilder();
		sb.append(dst.getId()).append(" (").append(weight).append(")");
		return sb.toString();
	}
	
	/**
	 * @return a string representation of the edge
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.src.getId() < this.dst.getId()) {
			sb.append(this.src.getId()).append(" <-").append(this.weight).append("-> ").append(this.dst.getId());
		} else {
			sb.append(this.dst.getId()).append(" <-").append(this.weight).append("-> ").append(this.src.getId());
		}

		return sb.toString();
	}

}
