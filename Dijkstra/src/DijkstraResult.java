import java.security.KeyException;
import java.util.ArrayList;
import java.util.HashSet;

class DijkstraResult {
	private Graph path;
	private int src;
	private int distance;

	public DijkstraResult(Graph path, int src, int distance) {
		this.src = src;
		this.path = path;
		this.distance = distance;
	}

	public Graph getPath() {
		return path;
	}

	public int getDistance() {
		return distance;
	}

	public String toString() {
		// TODO: Implementieren Sie die toString Methode nach der Spezifikation auf Blatt 11
		boolean test = true;
		String s = "";
		int laenge = 0;
		if (path.contains(distance)){
			ArrayList<Node> node = path.getNodList();
			s = "Kuerzester Pfad ";
			for (int i = 0;i < node.size();i++){
				if (node.get(i) != null){
					if (test){
						s += node.get(i).getId();
						test = false;
					}
					else {
						s += ", " + node.get(i).getId();
					}
				}
				ArrayList<Edge> edge = node.get(i).getList();
				for (int k = 0;k < edge.size();k++){
					if (edge.get(k) != null){
						laenge = laenge + edge.get(k).getWeight();
					}
				}
			}
			s += " mit Laenge " + laenge + " gefunden.";
			return s;
		}
		else {
			System.out.println("Es gib kein Pfad von Knoten " + src + " zu Knoten " + distance);
		}
		return s;
	}

	public Graph dijkstra(Graph g, int src, int dst)throws KeyException, IndexOutOfBoundsException, TooManyElementsException{

		Graph result = new Graph();
		int INFINITY = Integer.MAX_VALUE;
		ArrayList<Node> knoten = g.getNodList();
		HashSet<Node> weiss = new HashSet<>();
		HashSet<Node> schwarz  = new HashSet<>();

		// PQ initialisieren.
		MinPQ heap = new MinPQ(knoten.size());
		for (int i = 0;i < knoten.size();i++){
			HeapElement element = new HeapElement(INFINITY,knoten.get(i));
			heap.insert(element);
		}
		assert !heap.isEmpty(): "Heap wurde nicht intialisiert";
		// Erster Knoten auf 0 setzen.
		heap.decreaseDistance(g.getNode(src),0);
		// Alle Knoten mit weisser Farbe faerben.
		for (int i = 0;i < knoten.size();i++){	weiss.add(knoten.get(i));}

		while (!heap.isEmpty()){
			HeapElement min = heap.extractMin();
			if (weiss.contains(min)){
				weiss.remove(min);
				schwarz.add(min.getNode());
				result.addNode(min.getNode().getId());
				assert (!weiss.contains(min.getNode()) && schwarz.contains(min.getNode()) && !result.isEmpty()): "Knoten wurde nicht geloescht und nicht hinzugefuegt";
				//Distanzen bearbeiten.
				ArrayList<Edge> speicher = min.getNode().getList();
				for (int j = 0;j < speicher.size();j++){
					Edge edge = speicher.get(j);
					Node destination = edge.getDst();
					if (!schwarz.contains(destination)) {
						int newkey = min.getDistance() + edge.getWeight();
						int key = heap.distancearray(destination);
						if (newkey < key) {
							heap.decreaseDistance(destination, newkey);
						}
					}
				}
			}
		}
		return result;
	}





}
